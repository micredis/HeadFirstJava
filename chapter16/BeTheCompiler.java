import java.util.*;

public class BeTheCompiler {
	public static void main(String[] args) {
		// incompatible types: ArrayList<Animal> cannot be converted to ArrayList<Dog>
		// ArrayList<Dog> dogs1 = new ArrayList<Animal>();

		// incompatible types: ArrayList<Dog> cannot be converted to ArrayList<Animal>
		// ArrayList<Animal> animals1 = new ArrayList<Dog>();

		List<Animal> list = new ArrayList<Animal>();

		ArrayList<Dog> dogs = new ArrayList<Dog>();

		// incompatible types: ArrayList<Dog> cannot be converted to ArrayList<Animal>
		// ArrayList<Animal> animals = dogs;

		List<Dog> dogList = dogs;

		ArrayList<Object> objects = new ArrayList<Object>();

		List<Object> objList = objects;

		// incompatible types: ArrayList<Dog> cannot be converted to ArrayList<Object>
		// ArrayList<Object> objs = new ArrayList<Dog>();
	}
}