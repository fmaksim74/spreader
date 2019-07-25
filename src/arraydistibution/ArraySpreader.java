package arraydistibution;

import java.util.*;

public class ArraySpreader {
	private final int[] array;
	public int[] getArray() {
		return array;
	}

	private int arrayHash;
	public int getArrayHash() {
		return this.arrayHash;
	}

	private List<Integer> arrayHashes = new LinkedList<>();
	private int maxValue;
	private int maxIndex;

	private int stepCount;
	public int getStepCount() {
		this.SpreadArray();
		return this.stepCount;
	}
	
	private int cycleLength;
	public int getCycleLength() {
		this.SpreadArray();
		return this.cycleLength;
	}
	
	private long elpsedTime;
	public long getElapsedTime() {
		return this.elpsedTime;
	}
	public double getElapsedTimeInSeconds() {
		return this.elpsedTime / Math.pow(10, 9);
	}
	
	public void SpreadArray() {
		if (this.stepCount > 0) {
			return;
		}
		
		long startTime = System.nanoTime();
		
		while (!this.arrayHashes.contains(this.arrayHash)) {

			this.arrayHashes.add(this.arrayHash);

			int delta = this.maxValue / this.array.length;
			int reminder = this.maxValue % this.array.length;
			if (delta <= 0) {
				delta = 1;
				reminder = 0;
			}

			this.array[maxIndex++] = 0;

			int oldMaxIndex = this.maxIndex;
			int oldMaxValue = this.maxValue;

			this.maxValue = 0;

			while (oldMaxValue > reminder) {
				if (oldMaxIndex >= this.array.length) {
					oldMaxIndex = 0;
				}
				this.array[oldMaxIndex] += delta;

				if (this.array[oldMaxIndex] >= maxValue) {
					this.maxValue = this.array[oldMaxIndex];
					this.maxIndex = oldMaxIndex;
				}
				oldMaxValue -= delta;
				++oldMaxIndex;
			}

			while (reminder > 0) {
				if (oldMaxIndex >= this.array.length) {
					oldMaxIndex = 0;
				}
				++this.array[oldMaxIndex];

				if (this.array[oldMaxIndex] > this.maxValue) {
					this.maxValue = this.array[oldMaxIndex];
					this.maxIndex = oldMaxIndex;
				}
				--reminder;
				++oldMaxIndex;
			}

			this.arrayHash = Arrays.hashCode(this.array);
		}
		this.stepCount = this.arrayHashes.size();
		this.cycleLength = this.stepCount - this.arrayHashes.indexOf(this.arrayHash);
		this.elpsedTime = System.nanoTime() - startTime;
	}

	@Override
	public String toString() {
		return "ArraySpreader [array=" + Arrays.toString(array) + ", arrayHash=" + arrayHash + ", stepCount="
				+ stepCount + ", cycleLength=" + cycleLength + "]";
	}

	public ArraySpreader(int[] array) {
		this.array = array;
		// Initialize max value and it index
		int currentIndex = 0;
		for (int i = 0; i < this.array.length; ++i) {
			if (this.array[i] > this.maxValue) {
				maxValue = this.array[i];
				maxIndex = i;
			}
		}
	}
}
