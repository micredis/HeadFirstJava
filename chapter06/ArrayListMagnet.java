import java.util.*;

public class ArrayListMagnet {
	public static void printAL(ArrayList<String> al) {
		for (String element : al) {
			System.out.print(element + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		
		a.add(0, "zero");
		a.add(1, "one");
		a.add(2, "two");
		a.add(3, "three");
		printAL(a);

		if (a.contains("three")) {
			a.add("four");
		}
		a.remove(2);
		printAL(a);

		if (a.indexOf("four") != 4) {	// now the index of "four" is actually 3 (because of a.remove(2) 2 lines above)
			a.add(4, "4.2");
		}
		printAL(a);

		if (a.contains("two")) {		// there's no "two" element anymore (see a.remove(2) above)
			a.add("2.2");				// will not work
		}
		printAL(a);
	}
}