package com.feiniu.mybatis.util.sort.heap;

import java.util.Arrays;
import java.util.Random;

public class HeapUtil {
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
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			swap(arr, 0, i);
			adjustHeap(arr, 0, i);
		}
	}

	/**
	 * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
	 * 
	 * @param arr
	 * @param i
	 * @param length
	 */
	public static void adjustHeap(int[] arr, int i, int length) {
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			index++;
			if ((k + 1) < length && arr[k + 1] > arr[k]) {
				k++;
			}

			index++;
			if (arr[i] < arr[k]) {
				swap(arr, i, k);
				i = k;
			}
		}

	}

	/**
	 * 交换元素
	 * 
	 * @param arr
	 * @param a
	 * @param b
	 */
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}
