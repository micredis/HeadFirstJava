import java.io.*;

public class TestCeilFloor {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input a number: ");
		double num = Double.valueOf(br.readLine());
		System.out.println("The ceiling is " + Math.ceil(num));
		System.out.println("The floor is " + Math.floor(num));
		br.close();
	}
}