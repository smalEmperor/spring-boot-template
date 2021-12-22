package com.template.lock;


import com.template.config.UnblockThreadPoolExecutor;

import java.util.concurrent.ExecutorService;

public class Synchronized {

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

class Ticket {

    private int count = 30;


    public void sale(){
        if (count > 0) {
            count --;
            System.out.println("剩余票数:"+ count + "张");
        }
    }

}

class TicketOne {

    private int count = 30;

    public synchronized void sale(){
        if (count > 0) {
            count --;
            System.out.println("剩余票数:"+ count + "张");
        }
    }

}

class TicketTwo {

    private volatile int count = 30;

    public void sale(){
        if (count > 0) {
            count --;
            System.out.println("剩余票数:"+ count + "张");
        }
    }

}
