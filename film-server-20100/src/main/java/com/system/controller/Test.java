package com.system.controller;

public class Test {
    public static void main(String[] args) {
        // 获取args的第一个整数给n
        int n = Integer.parseInt(args[0]);
        for (int i = 2; i <n; i++) {
            getPrimeNumber(i);
        }
    }
    public static void getPrimeNumber(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if(n % i == 0) {
                return;
            }
        }
        System.out.println(n);
    }


    public static void mySort(int[] arr) {
        // 冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            // 每次比较的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 比较相邻的两个数，如果前面的数大于后面的数，则交换
                if(arr[j] > arr[j + 1]) {
                    // 交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }

        }
    }

}
