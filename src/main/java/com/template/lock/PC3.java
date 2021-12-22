package com.template.lock;


import com.template.config.UnblockThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PC3 {

    public static void main(String[] args) {
        Data3 data = new Data3();

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
                data.dec();
            }
        });

        customThreadPoolExecutor.execute(() -> {
            System.out.println("执行4");
            for (int i = 0; i < 10; i++) {
                data.add();
            }
        });

        customThreadPoolExecutor.shutdown();
    }

}


class Data3 {

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();
    private int num = 0;

    public void add() {
        lock.lock();
        try {
            // 虚假唤醒
           /* if (num != 0) {
                this.wait();
            }*/

            while (num != 0) {
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


        num ++;

        System.out.println("num加:"+ num);

        condition.signalAll();
    }

    public void dec() {
        lock.lock();
        try {
            /*if (num == 0) {
                this.wait();
            }*/

            while (num == 0) {
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        num --;

        System.out.println("num减:"+ num);

        condition.signalAll();
    }
}
