import java.util.Date;

public class TestDateFormat {
	public static void main(String[] args) {
		Date today = new Date();
		
		String dt = String.format("%tc", today);
		System.out.println(dt);

		String time = String.format("%tr", today);
		System.out.println(time);

		String wmd = String.format("%tA, %tB %td", today, today, today);
		System.out.println(wmd);

		String wdm = String.format("Today is %tA, %<tdth of %<tB.", today);
		System.out.println(wdm);
	}
}