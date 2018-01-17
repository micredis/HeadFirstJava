public class EnumTest {
	public enum Members { JERRY, BOBBY, PHIL };
	public Members selectedBandMember;

	public static void main(String[] args) {
		// Assigning an enum value to a variable
		Members n = Members.BOBBY;
		
		// We can compare enum instances using either
		// == or the .equals() method. Usually == is
		// considered better style.
		if (n.equals(Members.JERRY)) System.out.println("Jerrrry!");
		if (n == Members.BOBBY) System.out.println("Rat Dog");
		// "Rat Dog" will be printed.

		Members ifName = Members.PHIL;
		switch (ifName) {
			case JERRY:	System.out.print("make it sing ");
			case PHIL:	System.out.print("go deep ");
			case BOBBY:	System.out.println("Cassidy! ");
		}
		// It'll print: "go deep Cassidy! " (everything after the case PHIL,
		// i.e. case PHIL + case BOBBY). To print only the line that corresponds
		// to the one particular name, the words "break;" must be put after each
		// "case"-line.
	}
}