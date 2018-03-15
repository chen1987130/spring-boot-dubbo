package com.feiniu.mybatis.util.sort.heap;

import java.util.Arrays;
import java.util.Random;

public class MergeUtil {
	public static int index = 0;

	public static void main(String[] args) {
		int[] arr = new int[1000];
		for (int i = 0; i < 1000; i++) {
			arr[i] = new Random().nextInt(10000);
		}
		System.out.println(Arrays.toString(arr));
		sort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
		System.out.println(index);
	}

	public static void sort(int[] a, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			sort(a, low, mid);
			sort(a, mid + 1, high);
			// 左右归并
			merge(a, low, mid, high);
		}
	}

	public static void merge(int[] a, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		// 把较小的数先移到新数组中
		while (i <= mid && j <= high) {
			if (a[i] < a[j]) {
				index++;
				temp[k++] = a[i++];
			} else {
				index++;
				temp[k++] = a[j++];
			}
		}
		// 把左边剩余的数移入数组
		while (i <= mid) {
			index++;
			temp[k++] = a[i++];
		}
		// 把右边边剩余的数移入数组
		while (j <= high) {
			index++;
			temp[k++] = a[j++];
		}
		// 把新数组中的数覆盖nums数组
		for (int x = 0; x < temp.length; x++) {
			a[x + low] = temp[x];
		}
	}
}
