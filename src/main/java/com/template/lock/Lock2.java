package com.template.lock;


import com.template.config.UnblockThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

public class Lock2 {

    public static void main(String[] args) {

        Goods goods = new Goods();

        GoodsOne goodsOne = new GoodsOne();

        /*new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                //ticket.sale();
                ticketOne.sale();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                //ticket.sale();
                ticketOne.sale();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                //ticket.sale();
                ticketOne.sale();
            }
        }).start();*/

        ExecutorService customThreadPoolExecutor = new UnblockThreadPoolExecutor().getCustomThreadPoolExecutor();
        customThreadPoolExecutor.execute(() -> {
            System.out.println("执行1");
            for (int i = 0; i < 40; i++) {
                //ticket.sale();
                goodsOne.sale();
            }
        });

        customThreadPoolExecutor.execute(() -> {
            System.out.println("执行2");
            for (int i = 0; i < 40; i++) {
                //ticket.sale();
                goodsOne.sale();
            }
        });

        customThreadPoolExecutor.execute(() -> {
            System.out.println("执行3");
            for (int i = 0; i < 40; i++) {
                //ticket.sale();
                goodsOne.sale();
            }
        });

        customThreadPoolExecutor.shutdown();

    }
}

class Goods {

    private int count = 30;


    public void sale(){
        if (count > 0) {
            count --;
            System.out.println("剩余票数:"+ count + "张");
        }
    }

}

class GoodsOne {

    ReentrantLock lock = new ReentrantLock();

    private int count = 30;

    public void sale(){
        lock.lock();
        try {
            if (count > 0) {
                count --;
                System.out.println("剩余票数:"+ count + "张");
            }
        } finally {
            lock.unlock();
        }
    }

}

class GoodsTwo {

    private volatile int count = 30;

    public void sale(){
        if (count > 0) {
            count --;
            System.out.println("剩余票数:"+ count + "张");
        }
    }

}
