package thread.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author happy
 * 手动线程池
 * <p>
 * 自动创建线程池 只是对threadpool的封装
 */
public class ThreadPool {
    /**
     * 线程池的大小
     **/
    static int corePoolSize = 10;

    /**
     * 线程池的最大数
     **/
    static int maximumPoolSizeSize = 20;

    /**
     * 线程活动保持时间 当前线程池线程数量超过corePoolSize时,剩余的多久被销毁
     **/
    static long keepAliveTime = 1;
    /**
     * 任务队列 ----被提交但是未被执行的任务
     * 当请求超过线程池的数量,会缓存到任务队列中
     * SynchronousQueue 直接提交队列,是一个特殊的blockQueue
     * 如果达到最大的线程池数量,无法继续新建线程处理任务,就会执行拒绝任务
     * ArrayBlockingQueue 当线程达到最大限度,会保存在该队列中
     * 若超过队列的最大值才会执行拒绝操作
     * LinkBlockingQueue 无界队列  直到内存耗尽才会执行拒绝操作
     * PriorityBlockingQueue 优先队列 无界 保证每次都是线程自带优先级最高
     * 的先执行
     **/
    static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);

    /**
     * 当任务太多的时候,如何拒绝任务
     * 1.	AbortPolicy:直接抛出异常,阻止系统正常工作
     * 2.	CallerRunsPolicy:只要线程池未被关闭,直接在调用线程中调用该策略,会影响线程的性能
     * 3.	DiscardOldesPolicy:丢弃最老的请求,再次提交当前的任务
     * 4.	DiscardPolicy:，默默的丢弃无法处理的任务
     **/
    RejectedExecutionHandler handler;

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
