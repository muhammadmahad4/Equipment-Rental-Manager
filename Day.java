public class Day implements Cloneable, Comparable<Day>{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";

	//--------------------------CONSTRUCTORS------------------------------------------------//
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}

	public Day(String sDay) throws ExInvalidFormat{ //Constructor, simply call set(sDay)
		set(sDay);
	}
	//--------------------------------------------------------------------------------------------------------//

	//---------------------------------GETTER FUNCTIONS------------------------------------------------------//
		
		public int getDay(){return day;}
		public int getMonth(){return month;}
		public int getYear(){return year;}
	
		
	//-------------------------------------------------------------------------------------------------------//
	public Day IncrementDay(int d) {
		// Start with the current date
		int newYear = year;
		int newMonth = month;
		int newDay = day + d; // Start by adding days
	
		// Days in each month
		int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
		// Adjust for leap years if February is involved
		if (isLeapYear(newYear)) {
			daysInMonth[2] = 29; // February has 29 days in a leap year
		}
	
		// Loop to adjust the day, month, and year as needed
		while (newDay > daysInMonth[newMonth]) {
			newDay -= daysInMonth[newMonth]; // Move to the next month
			newMonth++; // Increment the month
	
			if (newMonth > 12) { // If month exceeds December
				newMonth = 1; // Reset to January
				newYear++; // Increment the year
			}
		}
	
		// Return the new Day object
		return new Day(newYear, newMonth, newDay);
	}

	//compareto function used for sorting
	@Override
	public int compareTo(Day another) {
		// Create a comparable integer representation of the date
		int thisDayInt = this.getYear() * 10000 + this.getMonth() * 100 + this.getDay();
		int anotherDayInt = another.getYear() * 10000 + another.getMonth() * 100 + another.getDay();
	
		return Integer.compare(thisDayInt, anotherDayInt);
	}

	//checking if overlap occurs between dates of borrow/request
	//d1 = start of borrow d2 =end of borrow d3 = start of reserve d4 = end of reserve
	public static boolean doRangesOverlap(Day start1, Day end1, Day start2, Day end2) {
		
		return (start1.compareTo(end2) <= 0) && (start2.compareTo(end1) <= 0);
	}


	
	// check if a given year is a leap year
	static public boolean isLeapYear(int y) {
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d) {
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	//non static valid function
	public boolean valid(){
		return Day.valid(this.year, this.month,this.day);
	}

	// Return a string for the day like dd MMM yyyy
	@Override
	public String toString() {
		return day+"-"+ MonthNames.substring((month-1)*3,(month)*3) + "-"+ year; // (month-1)*3,(month)*3
	}

	//clone function. used in cmdBorrow and cmdRequest to not send the same day objects but new ones
	@Override
	public Day clone(){ 
		Day copy=null;
		try{
			copy = (Day) super.clone();
			copy.day = this.getDay();
			copy.month = this.getMonth();
			copy.year = this.getYear();
		}
		catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		return copy;
	}

	public void set(String sDay) throws ExInvalidFormat{ //Set year,month,day based on a string like 01-Mar-2024
		String[] sDayParts = sDay.split("-");
		if (sDayParts.length != 3){
			throw new ExInvalidFormat();
		}
		else if(MonthNames.contains(sDayParts[1]) == false){
			throw new ExInvalidFormat();
		}
		else{
			try{
				this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
				this.year = Integer.parseInt(sDayParts[2]); 
				this.month = MonthNames.indexOf(sDayParts[1])/3+1;
				if(this.valid() == false){
					throw new ExInvalidFormat();
				}
			}catch(NumberFormatException e){
				throw new ExInvalidFormat();
			}
		}	
		
	}
}
