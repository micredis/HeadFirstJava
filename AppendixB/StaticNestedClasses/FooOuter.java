// Static Nested Class Test
// To run type:
// java Test

public class FooOuter {
	// A static nested class is just that--a class enclosed within
	// another, and marked with the static modifier:
	static class BarInner {
		void sayIt() {
			System.out.println("method of a static inner class");
		}
	}
}

class Test {
	public static void main(String[] args) {
		// Because a static nested class is _static_, you don't
		// use an instance of the outer class. You just use the
		// name of the class, the same way you invoke static
		// methods or access static variables:
		FooOuter.BarInner foo = new FooOuter.BarInner();
		foo.sayIt();
	}
}