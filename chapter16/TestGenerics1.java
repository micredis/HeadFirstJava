import java.util.*;

public class TestGenerics1 {
	public static void main(String[] args) {
		new TestGenerics1().go();
	}

	public void go() {
		// Declare and create an Animal array,
		// that holds both dogs ad cats
		Animal[] animals = {new Dog(), new Cat(), new Dog()};
		// Declare and create a Dog array,
		// that holds only Dogs (the compiler
		// won't let us put a Cat in)
		Dog[] dogs = {new Dog(), new Dog(), new Dog()};
		takeAnimals(animals);
		takeAnimals(dogs);
	}

	public void takeAnimals(Animal[] animals) {
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