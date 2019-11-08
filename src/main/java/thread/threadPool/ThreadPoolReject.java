package thread.threadPool;

import java.util.concurrent.*;

/**
 * 线程池拒绝策略
 *
 * @author happy
 **/
public class ThreadPoolReject {

    public static class myTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        myTask myTask = new myTask();
        ExecutorService ex = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<>(10),
                (r, executor) -> {
                    System.out.println(r.toString() + "is discard");
                }
        );
//        线程产生的速度远远大于处理速度
//        任务列表满员之后,会走拒绝策略
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            ex.submit(myTask);
            Thread.sleep(10);
        }
    }


}
