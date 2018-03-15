package com.feiniu.mybatis.util.sort.heap;

import java.util.Arrays;
import java.util.Random;

public class BubbleUtil {
	public static int index = 0;

	public static void main(String[] args) {
		int[] arr = new int[1000];
		for (int i = 0; i < 1000; i++) {
			arr[i] = new Random().nextInt(10000);
		}
		System.out.println(Arrays.toString(arr));
		sort(arr, arr.length);
		System.out.println(Arrays.toString(arr));
		System.out.println(index);
	}

	private static void sort(int[] arr, int size) {
		boolean finish = true;
		for (int i = 0; i < size - 1; i++) {
			index++;
			if (arr[i] > arr[i + 1]) {
				finish = false;
				swap(arr, i, i + 1);
			}
		}

		if (size > 0 && !finish) {
			sort(arr, size - 1);
		}
	}

	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
