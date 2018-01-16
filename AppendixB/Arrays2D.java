// Find a number and a sum of elements
// over the main diagonal in a 2D-Array
// ("main diagonal" includes all
// elements with i == j;
// "over the main diagonal" includes
// elements with i < j).

import java.io.*;

public class Arrays2D {
	private int[][] arr = null;

	public static void main(String[] args) throws IOException {
		new Arrays2D().go();
	}

	public void go() throws IOException {
		this.initArray();
		this.rndFill();
		this.printArray();
		System.out.println();
		System.out.println("Number of the elements over the main diagonal: " + this.countOverDiag());
		System.out.println("Sum of those elements: " + this.sumOverDiag());
	}

	public void initArray() throws IOException {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Number of rows: ");
		String sImax = console.readLine();
		while (!sImax.matches("\\d+")) {
			System.out.println("You must input a non-negative NUMBER: ");
			sImax = console.readLine();
		}

		System.out.print("Number of columns: ");
		String sJmax = console.readLine();
		while (!sJmax.matches("\\d+")) {
			System.out.println("You must input a non-negative NUMBER: ");
			sJmax = console.readLine();
		}

		console.close();

		int imax = Integer.parseInt(sImax);
		int jmax = Integer.parseInt(sJmax);

		arr = new int[imax][jmax];
	}

	public void rndFill() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = (int) (Math.random() * 10);
			}
		}
	}

	public int countOverDiag() {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (i < j) count++;
			}
		}
		return count;
	}

	public int sumOverDiag() {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (i < j) sum += arr[i][j];
			}
		}
		return sum;
	}

	public void printArray() {
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print("" + arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}