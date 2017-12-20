public class MyApp {
	public static void main(String[] args) {
		Foo foo = new Foo();
		Bar bar = new Bar();
		Bar foobar = new Bar(foo);
		foo.say();
		bar.say();
		foobar.say();
	}
}