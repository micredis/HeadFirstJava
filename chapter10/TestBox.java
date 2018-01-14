public class TestBox {
	Integer i; // null is the default value; must be assigned to run the class
	int j; // 0 is the default value

	public static void main(String[] args) {
		TestBox t = new TestBox();
		t.go();
	}

	public void go() {
		j = i; // throws NullPointerException (since i is not defined and its default value null cannot be assigned to int j)
		System.out.println(j);
		System.out.println(i);
		
		boolean b = new Boolean("true"); // there's no booleanValue() for unwrapping (since auto unboxing will be applied by itself)
		System.out.println(b);

		boolean boo = new Boolean("true").booleanValue(); // booleanValue() --> "manual" unwrapping
		System.out.println(boo);
	}
}