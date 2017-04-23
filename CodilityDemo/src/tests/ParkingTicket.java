package tests;

import java.text.SimpleDateFormat;

public class ParkingTicket {

	static SimpleDateFormat df = new SimpleDateFormat("HH:mm");
	
	public static int solution (String E, String L) {
		try {
			df.parse(L);
			df.parse(E);
		} catch (Exception e) {
			throw new IllegalArgumentException("Incorrect time format");
		}
		
		String[] entryT = E.split(":");
		String[] exitT = L.split(":");
		
		int entryTimeInMins = Integer.valueOf(entryT[0]) * 60 + Integer.valueOf(entryT[1]);
		int exitTimeInMins = Integer.valueOf(exitT[0]) * 60 + Integer.valueOf(exitT[1]);
		
		if (entryTimeInMins > exitTimeInMins) {
			throw new IllegalArgumentException("Entry time cannot be after exit time");
		}
		
		int minutesStayed = exitTimeInMins - entryTimeInMins;
		int hourCount = (int) Math.ceil(minutesStayed / 60.0);
		
		return 2 + 3 + ((hourCount - 1) * 4 );
	}
	
	public static void main (String[] args) {
		System.out.println(solution("03:40", "05:50"));
		System.out.println(solution("03:40", "07:50"));
		System.out.println(solution("03:40", "05:40"));
		System.out.println(solution("00:00", "05:50"));
	}
}
