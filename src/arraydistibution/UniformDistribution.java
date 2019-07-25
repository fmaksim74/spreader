package arraydistibution;

import java.util.*;

public class UniformDistribution {

	public static void printArray(int[] arr, String msg) {
		System.out.print(msg);
		Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
		System.out.printf("[ %d ]\r\n", Arrays.stream(arr).sum());
	}

	public static void main(String[] args) {
		int[] inputCollection = new int[] { 0, 2, 7, 0 };
		int[] equalCollection = null;
		List<int[]> listOfStepCollections = new LinkedList<>();

		printArray(inputCollection, "Input collection at start: ");

		int iterationCount = 0;
		int distanceBetweenEqualCollections = 0;
		int maxIndex = 0;
		int maxValue = 0;
		boolean collectionsEqual = false;

		// find first max value
		for (int i = 0; i < inputCollection.length; ++i) {
			if (inputCollection[i] > maxValue) {
				maxValue = inputCollection[i];
				maxIndex = i;
			}
		}

		while (!collectionsEqual) {

			int delta = maxValue / inputCollection.length;
			int reminder = maxValue % inputCollection.length;
			if (delta <= 0) {
				delta = 1;
				reminder = 0;
			}

			inputCollection[maxIndex++] = 0;
			int oldMaxIndex = maxIndex;
			int oldMaxValue = maxValue;
			maxValue = 0;

			while (oldMaxValue > reminder) {
				if (oldMaxIndex >= inputCollection.length) {
					oldMaxIndex = 0;
				}
				inputCollection[oldMaxIndex] += delta;
				if (inputCollection[oldMaxIndex] > maxValue) {
					maxValue = inputCollection[oldMaxIndex];
					maxIndex = oldMaxIndex;
				}
				oldMaxValue -= delta;
				++oldMaxIndex;
			}

			while (reminder > 0) {
				if (oldMaxIndex >= inputCollection.length) {
					oldMaxIndex = 0;
				}
				++inputCollection[oldMaxIndex];
				if (inputCollection[oldMaxIndex] > maxValue) {
					maxValue = inputCollection[oldMaxIndex];
					maxIndex = oldMaxIndex;
				}
				--reminder;
				++oldMaxIndex;
			}

			Iterator<int[]> it = listOfStepCollections.iterator();
			distanceBetweenEqualCollections = 0;
			while (it.hasNext()) {
				equalCollection = it.next();
				distanceBetweenEqualCollections++;
				if (Arrays.equals(inputCollection, equalCollection) && iterationCount > 0) {
					collectionsEqual = true;
					break;
				}
			}
			listOfStepCollections.add(Arrays.copyOf(inputCollection, inputCollection.length));
			printArray(inputCollection, String.format("Input collection at iteration %d: ", iterationCount));

			if (collectionsEqual) {
				continue;
			}
			++iterationCount;

			Thread.yield();
		}

		printArray(inputCollection, "Input collection: ");
		printArray(equalCollection, "Repeated collection: ");
		System.out.printf("Arrays are " + (collectionsEqual ? "" : "not ") + "equal\r\n");

		System.out.printf("Iterations: %d\r\n", iterationCount + 1);
		System.out.printf("Distance: %d\r\n", iterationCount - distanceBetweenEqualCollections + 1);

	}

}
