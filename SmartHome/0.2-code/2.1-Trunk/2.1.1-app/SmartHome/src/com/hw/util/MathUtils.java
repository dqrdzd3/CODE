package com.hw.util;

/**
 * @author 曾凡
 * @time 2015年8月19日 下午3:29:53
 */
public class MathUtils {
	public static int find(int[] a) {
		int max = 0;
		for (int j = 0; j < a.length - 1; j++) {
			for (int k = j + 1; k < a.length; k++) {
				if (a[j] < a[k]) {
					max = a[k];
				} else {
					max = a[j];
				}
			}
		}
		return max;
	}
}
