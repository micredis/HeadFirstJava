import java.util.*;

public class TestGenerics3 {
	public static void main(String[] args) {
		new TestGenerics3().go();
	}

	public void go() {
		ArrayList<Animal> animals = new ArrayList<>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Dog());
		takeAnimals(animals);

		ArrayList<Dog> dogs = new ArrayList<>();
		dogs.add(new Dog());
		dogs.add(new Dog());
		dogs.add(new Dog());
		takeAnimals(dogs);

		takeThing(animals, dogs);
	}

	// We use a wildcard ? here to create a method argument
	// that can accept an ArrayList of any Animal subtype
	// and at the same time will prevent RuntimeException,
	// so e.g. this line in the takeAnimals method:
	// animals.add(new Cat());
	// will lead to compilation error (which is much better
	// than RuntimeException), since this new cat can't be
	// added to a list of Dogs in this method (if we passed
	// it a Dogs list as an argument)
	public void takeAnimals(ArrayList<? extends Animal> animals) {
		for(Animal a : animals) {
			a.eat();
		}
	}

	// Alternatively, we can use a notation of takeAnimals method below
	// (it'll work the same as the method above)
	/*public <T extends Animal> void takeAnimals(ArrayList<T> animals) {
		for(Animal a : animals) {
			a.eat();
		}
	}*/

	public void takeThing(ArrayList<? extends Animal> one, ArrayList<? extends Animal> two) {
		for(Animal a : one) {
			a.eat();
		}
		for(Animal b : two) {
			b.eat();
		}
	}
}

class Animal {
	void eat() {
		System.out.println("animal eats");
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