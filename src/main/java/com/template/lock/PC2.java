package com.template.lock;

import com.template.common.UnblockThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PC2 {

    public static void main(String[] args) {
        Data2 data = new Data2();

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


class Data2 {

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

            num ++;

            System.out.println("num加:"+ num);

            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

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

            num --;

            System.out.println("num减:"+ num);

            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
