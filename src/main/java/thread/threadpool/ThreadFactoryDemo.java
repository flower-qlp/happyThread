package thread.threadpool;

import java.util.concurrent.*;

/**
 * @author happy
 * 自定义线程
 */
public class ThreadFactoryDemo {


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolReject.MyTask myTask = new ThreadPoolReject.MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                (r) -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true); //守护线程t
                    t.setName("happy thread:"+t.getId());
                    System.out.println("create" + t);
                    return t;
                },
                (r, executor) -> {
                    System.out.println(r.toString() + "discard");
                });

//        submit()和excute都可以,但是excute()不会返回结果,
//        如果需要抛出异常,捕获异常,则需要使用execute()
        for (int i = 0; i < 30; i++) {
            //es.execute(myTask);
            es.submit(myTask);
        }

        Thread.sleep(5000);
    }
}
