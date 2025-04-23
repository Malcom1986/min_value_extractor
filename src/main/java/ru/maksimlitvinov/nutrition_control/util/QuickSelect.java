package ru.maksimlitvinov.nutrition_control.util;

import java.util.List;

public class QuickSelect {
    public static int findNthMin(List<Integer> numbers, int n) {
        return quickSelect(numbers, 0, numbers.size() - 1, n);
    }

    private static int quickSelect(List<Integer> numbers, int left, int right, int n) {
        if (left == right) {
            return numbers.get(left);
        }
        int pivotIndex = partition(numbers, left, right);
        if (n == pivotIndex + 1) {
            return numbers.get(pivotIndex);
        } else if (n < pivotIndex + 1) {
            return quickSelect(numbers, left, pivotIndex - 1, n);
        } else {
            return quickSelect(numbers, pivotIndex + 1, right, n);
        }
    }

    private static int partition(List<Integer> numbers, int left, int right) {
        int pivot = numbers.get(right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (numbers.get(j) < pivot) {
                i++;
                swap(numbers, i, j);
            }
        }
        swap(numbers, i + 1, right);
        return i + 1;
    }

    private static void swap(List<Integer> numbers, int i, int j) {
        int temp = numbers.get(i);
        numbers.set(i, numbers.get(j));
        numbers.set(j, temp);
    }
}
