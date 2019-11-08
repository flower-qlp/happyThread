package thread.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author happy
 * 线程池的扩展
 * beforeExecute
 * afterExecute
 * 在ThreadPoolExecute.worker类中,调用线程池中的线程运行
 * 先运行beforeExecute
 * 最后运行afterExecute
 ***/
public class ThreadPoolExt {

    public static class PoolExtend implements Runnable {
        public String name;

        public PoolExtend(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程：" + name);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

//    这是线程池的方法
    public static void main(String[] args) throws InterruptedException {


        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(5),
                (r) -> {
                    Thread t = new Thread(r);
                    t.setName("happy thread" + new Random().nextInt());
                    return t;
                }
        ) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("线程:" + ((PoolExtend) r).name + "准备运行");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("线程:" + ((PoolExtend) r).name + "运行结束");

            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };


        for (int i = 0; i < 10; i++) {
            es.execute(new PoolExtend("happy：" + i));
            Thread.sleep(1000);
        }

//        发送关闭线程池的信号,线程池不在接受请求,等到线程运行完关闭线程池
        es.shutdown();
    }
}
