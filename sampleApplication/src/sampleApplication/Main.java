package sampleApplication;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello, I'm a fibunacci calculator!");

		System.out.println("Enter number upto which Fibonacci series to print: ");
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		System.out.println(fibonacci(number));
		scanner.close();
	}

	public static int fibonacci(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
		return fibonacci(number - 1) + fibonacci(number - 2);
	}
}
