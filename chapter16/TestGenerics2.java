import java.util.*;

public class TestGenerics2 {
	public static void main(String[] args) {
		new TestGenerics2().go();
	}

	// In comparison with TestGenerics1,
	// now a simple change from Animal[]
	// to ArrayList<Animal> is made
	public void go() {
		ArrayList<Animal> animals = new ArrayList<>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Dog());
		takeAnimals(animals);
		
		// The code below won't workm since
		// during the compilation the generics
		// tests will fail (we can put into
		// takeAnimals() only Animals (see above),
		// but neither Dogs nor Cats (as independent
		// ArrayLists):
		// ArrayList<Dog> dogs = new ArrayList<Dog>();
		// takeAnimals(dogs);
	}

	// In comparison with TestGenerics1
	// this method now takes an ArrayList
	// instead of an array
	public void takeAnimals(ArrayList<Animal> animals) {
		for (Animal a : animals) {
			a.eat();
		}
	}
}

// The simpligied Animal class hierarchy
abstract class Animal {
	void eat() {
		System.out.println("animal eating");
	}
}

class Dog extends Animal {
	void bark() {		
	}
}

class Cat extends Animal {
	void meow() {		
	}
}