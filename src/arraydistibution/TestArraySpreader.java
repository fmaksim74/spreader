package arraydistibution;

public class TestArraySpreader {

	public static void main(String[] args) {
		int[] input = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		long start = System.nanoTime();
		ArraySpreader spreader = new ArraySpreader();
		spreader.setArray(input);
		spreader.SpreadArray();
		long total = System.nanoTime() - start;

		System.out.println(spreader);
		System.out.println("Steps: " + spreader.getStepCount());
		System.out.println("Length: " + spreader.getCycleLength());
		System.out.printf("Elapsed: %.5f c%n", spreader.getElapsedTimeInSeconds());
		System.out.printf("Total: %.5f c%n", total / Math.pow(10, 9));
		System.out.println("--------------------------------------");

		input = new int[] { 10, 21, 32, 43, 54, 65, 76, 87, 98, 90 };
		start = System.nanoTime();
		spreader.setArray(input);
		spreader.SpreadArray();
		total = System.nanoTime() - start;

		System.out.println(spreader);
		System.out.println("Steps: " + spreader.getStepCount());
		System.out.println("Length: " + spreader.getCycleLength());
		System.out.printf("Elapsed: %.5f c%n", spreader.getElapsedTimeInSeconds());
		System.out.printf("Total: %.5f c%n", total / Math.pow(10, 9));
		System.out.println("--------------------------------------");

		input = new int[] { 0, 2, 7, 0 };
		start = System.nanoTime();
		spreader.setArray(input);
		spreader.SpreadArray();
		total = System.nanoTime() - start;

		System.out.println(spreader);
		System.out.println("Steps: " + spreader.getStepCount());
		System.out.println("Length: " + spreader.getCycleLength());
		System.out.printf("Elapsed: %.5f c%n", spreader.getElapsedTimeInSeconds());
		System.out.printf("Total: %.5f c%n", total / Math.pow(10, 9));
		System.out.println("--------------------------------------");

		input = new int[] { 0, 2, 8, 0 };
		start = System.nanoTime();
		spreader.setArray(input);
		spreader.SpreadArray();
		total = System.nanoTime() - start;

		System.out.println(spreader);
		System.out.println("Steps: " + spreader.getStepCount());
		System.out.println("Length: " + spreader.getCycleLength());
		System.out.printf("Elapsed: %.5f c%n", spreader.getElapsedTimeInSeconds());
		System.out.printf("Total: %.5f c%n", total / Math.pow(10, 9));
		System.out.println("--------------------------------------");

	}

}
