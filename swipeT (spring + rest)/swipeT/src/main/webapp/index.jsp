<!DOCTYPE html>
<html lang="en">
<head>
<script>
	function submit() {
	   var workerId = document.getElementById("workerId").value;
	   window.location.href = '/swipeT/jobs/'+workerId;
}
</script>
</head>
<body>
<h2>Swipe Test</h2>
<label>Worker ID:</label>&nbsp;<input type="number" name="workerId" id="workerId" min="0"/>
<button onclick="submit()">Find</button>
</body>
</html>
