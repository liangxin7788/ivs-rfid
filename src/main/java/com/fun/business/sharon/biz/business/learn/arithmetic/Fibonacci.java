package com.fun.business.sharon.biz.business.learn.arithmetic;

import java.util.Scanner;

/**
 * 递归求斐波那契数列第n项
 */
public class Fibonacci {

    public static void main(String[] args) {
        int n,fn;//n为第n项，fn为第n项的值

        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        fn = function(n);
        System.out.println("斐波那契数列第"+n+"项为："+fn);
    }

    private static int function(int n) {
        if(n == 1 || n == 2){
            return 1;
        }
        return function(n-1)+function(n-2);
    }

}
