package org.hmzb.random;

import java.util.Random;

public class RandomCracker {
    protected static final long a = 0x5deece66dL;
    protected static final long b = 0xbL;
    protected static final long m = (1L << 48) - 1;

    /**
     * @param xint0 第一次调用nextInt()获取的随机整数
     * @param xint1 第二次调用nextInt()获取的随机整数
     * @output 下一次的随机数值
     */
    public static void crack(int xint0, int xint1) {
        long i;
        long seed = -1L;
        long x0 = (xint0 & 0xFFFFFFFFL) << 16;
        long x1 = (xint1 & 0xFFFFFFFFL);
        for (i = 0; i < 0xFFFFL; i++) {
            seed = (((x0 + i) * a) + b) & m;
            if ((seed >>> 16) == x1) {

                break;
            }
            seed = -1L;
        }
        if (seed == -1L) {
            throw new RuntimeException("Input Error!");
        } else {

            System.out.println("The Cracked x2=" + (int) (((seed * a) + b & m) >>> 16));
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        int xint0 = r.nextInt();
        System.out.println(xint0);
        int xint1 = r.nextInt();
        System.out.println(xint1);
        crack(xint0, xint1);
        int xint2 = r.nextInt();
        System.out.println(xint2);
    }
}
