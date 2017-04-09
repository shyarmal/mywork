#!/bin/bash

inputFile=$1   		# input file location from standard input
propFile=$2			# properties file location from standard input
outputFile=$3		# output file location from standard input

# Check whether the file exists. If so clear its contents, else create an empty file. 
if [ -e $outputFile ]
then
    > $outputFile
else
    touch $outputFile
fi

# Check whether the input file exists.
if [ -e $inputFile ]
then
	if [ -f "$propFile" ]
	then
	  # Read properties and form variables with key names and assign corresponding values. 
	  while IFS='=' read -r key value
	  do
	    key=$(echo $key)
	    eval "${key}='${value}'"
	  done < "$propFile"
	
	else
	  echo "$propFile not found."
	fi

	# Read from input file and write to output
	while read LINE
	do
		# Substitute tokens to match property variables and evaluate them to replace with property values. Further µ, Þ and ¬ 
		# characters are used to swap with <, > and empty strings before and after tokens, respectively to perform eval without errors.
		x=$(echo $LINE| sed -r 's/(\[\[)/¬$/g; s/(\]\])/¬/g; s/</Þ/g; s/>/µ/g;')
		echo $(eval "echo $x") | sed -r 's/¬//g; s/Þ/</g; s/µ/>/g;' >> $outputFile
	done < $inputFile
else 
	echo "$inputFile not found !"	
fi

