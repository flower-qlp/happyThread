package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.threadpool.ThreadPool.newThreadPool;

/**
 * @author happy
 * 信号量
 **/
public class SemaphoreDemo implements Runnable {
    //    允许资源同时5个线程访问
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
//            申请
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            释放资源
            semaphore.release();
        }
    }

    public static void main(String[] args) {
//        创建线程池(自动创建)
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
//        for (int i = 0; i < 20; i++) {
//            executorService.submit(semaphoreDemo);
//        }
//        结果:线程每5个一组执行

        ThreadPoolExecutor executor = newThreadPool();
        for (int i = 1; i < 20; i++) {
            executor.execute(semaphoreDemo);
        }
    }
}
