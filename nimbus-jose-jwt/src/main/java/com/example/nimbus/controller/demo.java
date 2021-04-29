package com.example.nimbus.controller;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: demo
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2021/4/28 10:08
 **/
public class demo {

    /**
     * 最大17bit的序列号是131071
     */
    public final static int MAX_ORDER_NO = 131071;
    /**
     * 时间戳的掩码41bit
     */
    public final static long TIME_CODE = Long.MAX_VALUE >>> 22;
    /**
     * 2015-01-01 00:00:00
     * 1420041600210
     * 因为我们的生成器可以使用69年，而我们想在这些时间里面，生成出来的id是逐渐自增的。
     * 所以我这里指定了从什么时候开始使用id生成器。
     */
    public final static long START_TIME = 1420041600210L;
    /**
     * 机器码 （0-31）
     */
    public final long MACHINE_CODE;
    /**
     * 用于生成序列号
     */
    public AtomicInteger orderNo;

    public demo() {
        if (1L < 0 || 1L > 31) {
            throw new IllegalArgumentException("请注意，1、机器码在多台机器或应用间是不允许重复的！2、机器码取值仅仅在0~31之间");
        }
        this.MACHINE_CODE = 1L;
        orderNo = new AtomicInteger(0);
    }

    public Long generateLong() {
        //1.与基准时间对其，得到相对时间
        long timeMillis = System.currentTimeMillis();
        System.out.println(Long.toBinaryString(timeMillis));
        long currentTimeMillis = timeMillis - START_TIME;
        System.out.println("------------------------------------------");
        System.out.println(Long.toBinaryString(currentTimeMillis));
        //2.保留相对时间的低41bit
        currentTimeMillis = currentTimeMillis & TIME_CODE;
        System.out.println(Long.toBinaryString(TIME_CODE));
        System.out.println(Long.toBinaryString(currentTimeMillis));
        System.out.println("------------------------------------------");
        //3、将1到41bit移到高位去 就是23~63。
        currentTimeMillis = currentTimeMillis << 22;
        System.out.println(Long.toBinaryString(currentTimeMillis));

        /*
         * 序列号自增1和获取
         * 注意：先增加再取值。
         */
        int orderNo = this.orderNo.incrementAndGet();
        do {
            if (orderNo > MAX_ORDER_NO) {
                //如果超过了最大序列号   则重置为0
                if (this.orderNo.compareAndSet(orderNo, 0)) {
                    //这里使用cas操作，所以不需要加锁    1、操作失败了   则表示别的线程已经更改了数据，则直接进行自增并获取则可以了
                    orderNo = 0;
                } else {
                    //注意：先增加再取值。
                    orderNo = this.orderNo.incrementAndGet();
                }
            }
        } while (orderNo > MAX_ORDER_NO);

        //符号位（1）bit、时间戳（2~42）bit | 序列号（43~59）bit | 机器码（60~64）bit
        System.out.println(Long.toBinaryString(orderNo << 5));
        System.out.println(Long.toBinaryString(MACHINE_CODE));
        return currentTimeMillis | (orderNo << 5) | MACHINE_CODE;
    }

    public static void main(String[] args) {
        demo demo = new demo();
        Long aLong = demo.generateLong();
        System.out.println(Long.toBinaryString(aLong));
        System.out.println(Long.toBinaryString(aLong).length());
        System.out.println(aLong);
        System.out.println(Long.toBinaryString(-1L));
        System.out.println(Long.toBinaryString(-1L << 5));
        System.out.println(Long.toBinaryString(-1L ^(-1L << 5)));
    }
}
