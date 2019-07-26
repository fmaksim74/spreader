package arraydistibution;

import java.util.Arrays;

public class TestArraySpreader {
	private static ArraySpreader spreader = new ArraySpreader();

	private static void doArray(int[] array) {
		
		System.out.println(Arrays.toString(array));
		long start = System.nanoTime();
		spreader.setArray(array).SpreadArray();
		long total = System.nanoTime() - start;

		System.out.println(spreader);
		System.out.println("Steps: " + spreader.getStepCount());
		System.out.println("Length: " + spreader.getCycleLength());
		System.out.printf("Elapsed: %.7f s%n", spreader.getElapsedTimeInSeconds());
		System.out.printf("Total: %.7f s%n", total / Math.pow(10, 9));
		System.out.println("--------------------------------------");
	}

	public static void main(String[] args) {
		doArray(new int[] { 0, 2, 7, 0 });
		doArray(new int[] { 0, 2, 8, 0 });
	}

}
