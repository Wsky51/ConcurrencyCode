package com.wuyi.concurrency;

import java.math.BigDecimal;

/**
 * Created by LENOVO on 2017/5/23.
 */
public class Demo {
    public static void main(String[] args) {

        BigDecimal decimal=new BigDecimal("123.456");
        String s = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        System.out.println(decimal.setScale(2,BigDecimal.ROUND_HALF_DOWN));
    }
}
