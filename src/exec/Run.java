package exec;

import java.util.Scanner;

import part1.Classifier;

public class Run {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("This program has 3 parts:");
		System.out.println("Type part1 for the k-nearest neighbour implementation.");
		String part = scan.next();
		if (part.equals("part1")) {
			System.out.println("Enter k value:");
			new Classifier(args[0], args[1], Integer.parseInt(scan.next()));
		} else {
			return;
		}

	}
}
