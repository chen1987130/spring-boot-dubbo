package com.feiniu.mybatis.util.sort.heap;

import java.util.Arrays;
import java.util.Random;

public class FastUtil {
	public static int index = 0;

	public static void main(String[] args) {
		int[] arr = new int[1000];
		for (int i = 0; i < 1000; i++) {
			arr[i] = new Random().nextInt(10000);
		}
		System.out.println(Arrays.toString(arr));
		sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(index);
	}

	public static void sort(int[] arr) {
		fastSort(arr, 0, arr.length - 1, 0, arr.length - 1);
	}

	private static void fastSort(int[] arr, int i, int j, int start, int end) {
		for (; j > 0 && j > i; j--) {
			index++;
			if (arr[j] < arr[i]) {
				swap(arr, i, j);
				break;
			}
		}

		for (; i < j; i++) {
			index++;
			if (arr[i] > arr[j]) {
				swap(arr, i, j);
				break;
			}
		}

		if (i != j) {
			fastSort(arr, i, j, start, end);
		} else {
			if (start != i) {
				fastSort(arr, start, i - 1, start, i - 1);
			}
			if (end != i) {
				fastSort(arr, i + 1, end, i + 1, end);
			}
		}
	}

	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
