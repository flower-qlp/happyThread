package thread.guava;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author happy
 * MoreExecutor 是Guava中对线程的补充
 */
public class DirectExecutorDemo {

    public static void main(String[] args) {
//        任何一个可以运行的Runnable实例的模块都可以视为线程池
//        Executor executor= MoreExecutors.directExecutor();
//        executor.execute(()->{
//            System.out.println("I am running in "+Thread.currentThread().getName());
//        });

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
//        将线程变成守护线程
        MoreExecutors.getExitingExecutorService(executor);
        executor.execute(() -> {
            System.out.println("I am running in " + Thread.currentThread().getName());
        });
    }

}
