package arraydistibution;

import java.util.*;

public class ArraySpreader {
	private int[] array;

	public int[] getArray() {
		return array;
	}

	public ArraySpreader setArray(int[] array) {
		this.array = array;
		this.arrayHash = Arrays.hashCode(this.array);
		this.arrayHashes.clear();
		this.cycleLength = 0;
		this.elapsedTime = 0;
		this.maxIndex = 0;
		this.maxValue = 0;
		this.stepCount = 0;
		return this;
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

	private long elapsedTime;

	public long getElapsedTime() {
		return this.elapsedTime;
	}

	public double getElapsedTimeInSeconds() {
		return this.elapsedTime / Math.pow(10, 9);
	}

	public ArraySpreader SpreadArray() {
		if (this.array == null) {
			return this;
		}
		if (this.stepCount > 0) {
			return this;
		}

		long startTime = System.nanoTime();

		// Initialize max value and max value index
		for (int i = 0; i < this.array.length; ++i) {
			if (this.array[i] > this.maxValue) {
				maxValue = this.array[i];
				maxIndex = i;
			}
		}

		// System.out.println(Arrays.toString(this.array) + " maxIndex="+maxIndex + " maxValue=" + maxValue);

		while (!this.arrayHashes.contains(this.arrayHash)) {

			this.arrayHashes.add(this.arrayHash);

			int delta = this.maxValue / this.array.length;
			if (delta <= 0) {
				delta = 1;
			}

			this.array[maxIndex] = 0;

			int oldMaxIndex = this.maxIndex;
			int oldMaxValue = this.maxValue;
			int arraySize = this.array.length;

			this.maxValue = 0;

			while (arraySize > 0 || oldMaxValue > 0) {

				++oldMaxIndex;

				if (oldMaxIndex >= this.array.length) {
					oldMaxIndex = 0;
				}

				if (oldMaxValue >= delta) {
					this.array[oldMaxIndex] += delta;
					oldMaxValue -= delta;
				} else {
					if (oldMaxValue > 0) {
						++this.array[oldMaxIndex];
						--oldMaxValue;
					}
				}

				if (this.array[oldMaxIndex] >= maxValue) {
					this.maxValue = this.array[oldMaxIndex];
					this.maxIndex = oldMaxIndex;
				}

				--arraySize;
			}

			this.arrayHash = Arrays.hashCode(this.array);
			//System.out.println(Arrays.toString(this.array) + " maxIndex="+maxIndex + " maxValue=" + maxValue);
		}
		this.stepCount = this.arrayHashes.size();
		this.cycleLength = this.stepCount - this.arrayHashes.indexOf(this.arrayHash);
		this.elapsedTime = System.nanoTime() - startTime;
		return this;
	}

	@Override
	public String toString() {
		return "ArraySpreader [array=" + (this.array != null ? Arrays.toString(array) : "empty") + ", arrayHash="
				+ arrayHash + ", stepCount=" + stepCount + ", cycleLength=" + cycleLength + ", elapsedTime="
				+ elapsedTime + "]";
	}

	public ArraySpreader() {
	}

	public ArraySpreader(int[] array) {
		this.setArray(array);
	}
}
