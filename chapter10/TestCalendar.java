import java.util.Calendar;

public class TestCalendar {
	public static void main(String...args) {
		Calendar c = Calendar.getInstance();
		c.set(1976, 1, 22, 16, 30);
		long day1 = c.getTimeInMillis();
		day1 += 1000 * 60 * 60; // add an hour
		c.setTimeInMillis(day1);
		System.out.println("New hour " + c.get(c.HOUR_OF_DAY));
		c.add(c.DATE, 35);
		System.out.println("Add 35 days " + c.getTime());
		c.roll(c.DATE, 35);
		System.out.println("Roll 35 days " + c.getTime());
		c.set(c.DATE, 9);
		System.out.println("Set to 9 " + c.getTime());
	}
}