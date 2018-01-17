public class HfjEnum {
	enum Names {
		JERRY("lead guitar") {
			public String sings() {		// This is so-called "constant-specific class body".
				return "plaintively";	// Think of it as overriding the basic enum method
			}							// (in this case the "sing()" method), if sing() is
		},								// called on a variable with an enum value of JERRY.
		BOBBY("rhythm guitar") {
			public String sings() {		// The same thing ("constant-specific class body")
				return "hoarsely";		// as described above, but for BOBBY.
			}
		},
		PHIL("bass");
		// "lead guitar", "rhythm guitar", "bass" are arguments
		// passed in to the constructor declared below.

		private String instrument;

		// This is the enum's constructor. It runs once for each declared enum value
		// (in this case it runs three times).
		Names(String instrument) {
			this.instrument = instrument;
		}
		public String getInstrument() {
			return this.instrument;
		}
		// The basic "sings()" method is only called when the enum value
		// has no constant-specific class body.
		public String sings() {
			return "occasionally";
		}
	} // close enum

	public static void main(String[] args) {
		// Every enum comes with a built-in "values()" method
		// which is typically used in a "for" loop as shown:
		for(Names n : Names.values()) {
			System.out.print(n);
			System.out.print(", instrument: " + n.getInstrument());
			System.out.println(", sings: " + n.sings());
		}
	}
}