package com.yhbc.base;

import com.yhbc.base.framework.rx.RxSubscriber;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        rxTest();
        mayTest();

    }

    private void rxTest() {
        System.out.println("main start" + Thread.currentThread());
        Observable.just(1).subscribeOn(Schedulers.newThread()).subscribe(new RxSubscriber<Integer>() {
            @Override
            protected void onSuccess(Integer result) {
                System.out.println("ss  onSuccess" + Thread.currentThread());

                long a = 0;
                for (int i = 0; i < 1000000; i++) {
                    a += i;
                }
                System.out.println("Runnable" + a + " " + Thread.currentThread());
                System.out.println("ee onSuccess" + Thread.currentThread());
            }

            @Override
            protected void onFailure(String errorMsg) {
                System.out.println("onFailure" + Thread.currentThread());
            }
        }).unsubscribe();
        System.out.println("main end" + Thread.currentThread());
    }

    //四舍五入精度测试用例
    private void mayTest() {
        maybe(0.4);
        maybe(0.41);
        maybe(0.481);
        maybe(0.485);
        maybe(0.486);
        maybe(1.4);
        maybe(1.41);
        maybe(1.481);
        maybe(1.485);
        maybe(1.486);
        maybe(0.01);
        maybe(0.071);
        maybe(0.075);
        maybe(0.076);
        maybe(0.001);
        maybe(0.005);
        maybe(0.007);
        maybe(0.015);


        maybe(-0.4);
        maybe(-0.41);
        maybe(-0.481);
        maybe(-0.485);
        maybe(-0.486);
        maybe(-1.4);
        maybe(-1.41);
        maybe(-1.481);
        maybe(-1.485);
        maybe(-1.486);
        maybe(-0.01);
        maybe(-0.071);
        maybe(-0.075);
        maybe(-0.076);
        maybe(-0.001);
        maybe(-0.005);
        maybe(-0.007);
        maybe(-0.015);
    }

    private void maybe(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        String val = String.valueOf(num);
        BigDecimal b1 = new BigDecimal(num);
        System.out.println("num = " + val);
        System.out.println("val = " + val);

        String s1 = decimalFormat.format(b1);
        Double d1 = Double.parseDouble(s1);
        System.out.println("b1 = " + b1);
        System.out.println("s1 = " + s1);
        System.out.println("d1 = " + d1);

        System.out.println("");

        BigDecimal bs = new BigDecimal(val);
        String ss = decimalFormat.format(bs);
        Double ds = Double.parseDouble(ss);
        System.out.println("bs = " + bs);
        System.out.println("ss = " + ss);
        System.out.println("ds = " + ds);

        System.out.println("");
        System.out.println("=================================================================");
    }
}