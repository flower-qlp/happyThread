package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.threadPool.ThreadPool.newThreadPool;

/**
 * @author happy
 * 计数器倒数
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
    static CountDownLatch countDownLatch = new CountDownLatch(10);

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " is going on ...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    /***
     * countDownLatch计数设置10
     * 当有10条线程完成任务 则等待结束,主线程继续
     * ***/
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = newThreadPool();
        for (int i = 0; i < 30; i++) {
            executor.execute(countDownLatchDemo);
        }
        countDownLatch.await();
        System.out.println("全部运行结束");
        executor.shutdown();
    }
}
