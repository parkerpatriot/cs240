package Dev.CS240.samples;

import java.util.Random;

public class HelloWorld {
	public static void main (String[] args) {
		// Random generator = new Random();
		// System.out.println(generator.nextInt());
		// String stringlist[] = null;
		System.out.println(Integer.parseInt("700"));

		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);

		if (a==3 && b==4){
			System.out.println("Condition is true!");
		}

		String hi;
		hi = String.format("P3\n%d %d\n", 200, 50);
		System.out.println(hi);
		System.out.println("Hello world!");
	}
}