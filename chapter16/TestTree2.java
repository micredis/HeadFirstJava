import java.util.*;

// an approach in which we use
// the TreeSet's overloaded constructor
// that takes a Comparator

public class TestTree2 {
	public static void main(String[] args) {
		new TestTree2().go();
	}

	public void go() {
		Book2 b1 = new Book2("How Cats Work");
		Book2 b2 = new Book2("Remix your Body");
		Book2 b3 = new Book2("Finding Emo");
		BookComparator bCompare = new BookComparator();
		TreeSet<Book2> tree = new TreeSet<Book2>(bCompare);
		tree.add(b1);
		tree.add(b2);
		tree.add(b3);
		System.out.println(tree);
	}
}

class BookComparator implements Comparator<Book2> {
	public int compare(Book2 one, Book2 two) {
		return ((one.title).compareTo(two.title));
	}
}

class Book2 {
	String title;
	public Book2(String t) {
		title = t;
	}
	public String toString() {
		return title;
	}
}