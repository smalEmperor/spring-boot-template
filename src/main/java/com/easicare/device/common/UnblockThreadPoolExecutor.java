package com.easicare.device.common;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java线程池
 * @author df
 */
public class UnblockThreadPoolExecutor {

	private ThreadPoolExecutor pool = null;
	/**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----4 CPU核心数*2（最优）
     * maximumPoolSize 最大线程池大小----200 （根据业务密集度计算）
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.SECONDS
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 							即当提交第41个任务时(前面线程都没有执行完),
     * 						            任务会交给RejectedExecutionHandler来处理
     * 
     * 如何来设置
	 * 需要根据几个值来决定
	 * tasks ：每秒的任务数，假设为500~1000
	 * taskcost：每个任务花费时间，假设为0.1s
	 * responsetime：系统允许容忍的最大响应时间，假设为1s
	 * 做几个计算
	 * corePoolSize = 每秒需要多少个线程处理？ 
	 * threadcount = tasks/(1/taskcost) =tasks*taskcout =  (500~1000)*0.1 = 50~100 个线程。corePoolSize设置应该大于50
	 * 根据8020原则，如果80%的每秒任务数小于800，那么corePoolSize设置为80即可
	 * queueCapacity = (coreSizePool/taskcost)*responsetime
	 * 计算可得 queueCapacity = 80/0.1*1 = 80。意思是队列里的线程可以等待1s，超过了的需要新开线程来执行
	 * 切记不能设置为Integer.MAX_VALUE，这样队列会很大，线程数只会保持在corePoolSize大小，当任务陡增时，不能新开线程来执行，响应时间会随之陡增。
	 * maxPoolSize = (max(tasks)- queueCapacity)/(1/taskcost)
	 * 计算可得 maxPoolSize = (1000-80)/10 = 92
	 * （最大任务数-队列容量）/每个线程每秒处理能力 = 最大线程数
	 * rejectedExecutionHandler：根据具体情况来决定，任务不重要可丢弃，任务重要则要利用一些缓冲机制来处理
	 * keepAliveTime和allowCoreThreadTimeout采用默认通常能满足
     */
	public UnblockThreadPoolExecutor() {
		 init();
	}
	 
    public void init() {
    	pool = new ThreadPoolExecutor(3,92,60L,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(80),new CustomThreadFactory(), new CustomRejectedExecutionHandler());
    }

	public void destory() {
        if(pool !=null) {
            pool.shutdownNow();
        }
    }
 
    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }

    /**
     * 自定义拒绝策略
     */
    private class CustomRejectedExecutionHandler implements  RejectedExecutionHandler {
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
    }

    /**
     * 自定义线程工厂
     */
    private class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName =  UnblockThreadPoolExecutor.class.getSimpleName()+count.addAndGet(1);
 
            t.setName(threadName);
            return t;
        }
    }
}
