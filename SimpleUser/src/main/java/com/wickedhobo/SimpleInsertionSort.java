package com.wickedhobo;

public class SimpleInsertionSort {

  private int[] toSort;

  public int[] returnSorted(int[] unsorted) {
    toSort = unsorted;

    for (int i = 0; i < toSort.length; i++) {
      int value = toSort[i];
      int index = i - 1;

      while (index >= 0 && toSort[index] < value) {
        toSort[index + 1] = toSort[index];
        index = index - 1;
      }
      toSort[index + 1] = value;
    }
    return toSort;
  }
}
