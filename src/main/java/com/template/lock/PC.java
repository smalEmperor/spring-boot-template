package com.template.lock;


import com.template.config.UnblockThreadPoolExecutor;

import java.util.concurrent.ExecutorService;

public class PC {

    public static void main(String[] args) {
        Data data = new Data();

        ExecutorService customThreadPoolExecutor = new UnblockThreadPoolExecutor().getCustomThreadPoolExecutor();

        customThreadPoolExecutor.execute(() -> {
            System.out.println("执行1");
            for (int i = 0; i < 10; i++) {
                data.add();
            }
        });

        customThreadPoolExecutor.execute(() -> {
            System.out.println("执行2");
            for (int i = 0; i < 10; i++) {
                data.dec();
            }
        });

        customThreadPoolExecutor.execute(() -> {
            System.out.println("执行3");
            for (int i = 0; i < 10; i++) {
                data.add();
            }
        });

        customThreadPoolExecutor.execute(() -> {
            System.out.println("执行4");
            for (int i = 0; i < 10; i++) {
                data.dec();
            }
        });

        customThreadPoolExecutor.shutdown();
    }

}


class Data {

    private int num = 0;

    public synchronized void add() {

        try {
            // 虚假唤醒
           /* if (num != 0) {
                this.wait();
            }*/

            while (num != 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        num ++;

        System.out.println("num加:"+ num);

        this.notifyAll();
    }

    public synchronized void dec() {

        try {
            /*if (num == 0) {
                this.wait();
            }*/

            while (num == 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num --;

        System.out.println("num减:"+ num);

        this.notifyAll();

    }
}
