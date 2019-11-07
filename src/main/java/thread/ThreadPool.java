package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author happy
 * 手动线程池
 */
public class ThreadPool {
    /**
     * 线程池的大小
     **/
    static int corePoolSize = 10;

    /**
     * 线程池的最大数
     **/
    static int maximumPoolSizeSize =20;

    /**
     * 线程活动保持时间
     **/
    static long keepAliveTime = 1;
    /**
     * 任务队列
     **/
    static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);

    public static ThreadPoolExecutor newThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSizeSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue);
        return executor;
    }

    public static ThreadPoolExecutor newThreadPool(int corePoolSize,
                                                   int maximumPoolSizeSize,
                                                   long keepAliveTime,
                                                   ArrayBlockingQueue workQueue) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSizeSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue);
        return executor;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSizeSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue);
        //提交一个任务
        executor.execute(() -> System.out.println("ok"));
    }

}
