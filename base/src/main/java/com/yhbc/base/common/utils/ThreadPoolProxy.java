package com.yhbc.base.common.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建时间： 2017/9/11 11:25
 * 类名：ThreadPoolProxy
 * 描述: 线程池工具类
 */

public class ThreadPoolProxy {

//ThreadPoolExecutor

    //通过ThreadPoolExecutor的代理类来对线程池的管理
    public static class ThreadPollProxy {
//        private ThreadPoolExecutor poolExecutor;//线程池执行者 ，java内部通过该api实现对线程池管理
//        //核心线程数
//        private int corePoolSize;
//        //最大线程数
//        private int maximumPoolSize;
//        //保持活动时间
//        private long keepAliveTime;


//        public ThreadPollProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
//            this.corePoolSize = corePoolSize;
//            this.maximumPoolSize = maximumPoolSize;
//            this.keepAliveTime = keepAliveTime;
//        }
        // mark 线程池部分
        private static final int CORE_POOL_SIZE = 4;
        private static final int MAXIMUM_POOL_SIZE = 8;
        private static final int KEEP_ALIVE = 0;
        private static final BlockingQueue<Runnable> linkedQueue = new LinkedBlockingDeque<>();
        private static final ThreadFactory sThreadFactory = new ThreadFactory() {

            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                LogUtils.i("AsyncTask_name", "AsyncTask #" + mCount.getAndIncrement());
                return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
            }
        };

        public static final ThreadPoolExecutor CUSTOM_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, linkedQueue, sThreadFactory,
                new ThreadPoolExecutor.DiscardOldestPolicy());

        private static final BlockingQueue<Runnable> httpQueue = new ArrayBlockingQueue<>(MAXIMUM_POOL_SIZE);

        public static final ThreadPoolExecutor HTTP_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, httpQueue, sThreadFactory,
                new ThreadPoolExecutor.DiscardOldestPolicy());

        /**
         * 执行http请求,当请求过多时,会依据策略抛弃最老的等待请求
         *
         * @param run run
         */
        public void executeHttp(Runnable run) {
            //最好不要直接用new Thread去启用线程,这样会消耗大量的内存, by xuhaijiang
//            new Thread(run).start();
            if (null != run) {
                HTTP_POOL_EXECUTOR.execute(run);
            }
        }

        //对外提供一个执行任务的方法
        public synchronized void execute(Runnable run) {
            //最好不要直接用new Thread去启用线程,这样会消耗大量的内存, by xuhaijiang
//            new Thread(run).start();
            if (null != run) {
                CUSTOM_POOL_EXECUTOR.execute(run);
            }
        }

        //对外提供一个执行任务的方法
//        public synchronized void executeThreadSync(Runnable run) {
//            if (poolExecutor == null || poolExecutor.isShutdown()) {
//                poolExecutor = new ThreadPoolExecutor(
//                        //核心线程数量
//                        corePoolSize,
//                        //最大线程数量
//                        maximumPoolSize,
//                        //当线程空闲时，保持活跃的时间
//                        keepAliveTime,
//                        //时间单元 ，毫秒级
//                        TimeUnit.SECONDS,
//                        //线程任务队列
//                       new LinkedBlockingDeque<Runnable>() );
//
//            }
//
//            poolExecutor.execute(run);
////            //开启的活动的线程数
//            LogUtils.i("活动线程----"+poolExecutor.getActiveCount());
////            开启的线程数
//            LogUtils.i("线程池中线程----"+poolExecutor.getPoolSize());
//        }

    }

    //通过ThreadPoolExecutor的代理类来对线程池的管理
    private static ThreadPollProxy mThreadPollProxy;

    //单列对象
    public static ThreadPollProxy getThreadPollProxy() {
//        synchronized (ThreadPollProxy.class) {
//            if (mThreadPollProxy == null) {
//                mThreadPollProxy = new ThreadPollProxy(2, Integer.MAX_VALUE, 2L);
//            }
//        }
        synchronized (ThreadPollProxy.class) {
            if (mThreadPollProxy == null) {
                mThreadPollProxy = new ThreadPollProxy();
            }
        }
        return mThreadPollProxy;
    }

}
