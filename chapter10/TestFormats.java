public class TestFormats {
	public static void main(String[] args) {
		String username = "Вася";
		String hey = String.format("Hello, %s!", username);
		System.out.println(hey);

		String str = String.format("%, d", 1000000000);		
		System.out.println(str);

		String btf = String.format("I have %.2f bugs to fix", 3765.097749758);
		System.out.println(btf);

		String fmt = String.format("%,6.1f", 42.000);
		System.out.println(fmt);

		String hex = String.format("%x", 15);
		System.out.println(hex);

		String chr = String.format("%c", 42);
		System.out.println(chr);

		double one = 856.74321;
		int two = 1000;
		String points = String.format("You've scored %,.2f points out of %,d.", one, two);
		System.out.println(points);
	}
}