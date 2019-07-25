package arraydistibution;

import java.util.Arrays;

public class TestArraySpreader {

	public static void main(String[] args) {
		int[] input = new int[] { 1000, 2000, 7000, 3000, 235, 23423, 234346, 9678, 123, 563 };

		ArraySpreader spreader = new ArraySpreader(input);
		spreader.SpreadArray();

		System.out.println(Arrays.toString(spreader.getArray()));
		System.out.println("Steps: " + spreader.getStepCount());
		System.out.println("Length: " + spreader.getCycleLength());
		System.out.printf("Elapsed: %.5f c%n", spreader.getElapsedTimeInSeconds());

	}

}
