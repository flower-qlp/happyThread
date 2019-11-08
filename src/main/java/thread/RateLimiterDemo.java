package thread;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 限流Guava
 * RateLimiter使用令牌限流算法
 *
 * @author happpy
 **/
public class RateLimiterDemo {

    /**
     * 限制每秒处理的请求个数
     **/
    static RateLimiter limiter = RateLimiter.create(2);

    public static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        /**运行结果:每秒执行俩个线程**/
        for (int i = 0; i < 50; i++) {
            limiter.acquire();
//            阻塞的线程请求丢弃,由于for循环的效率很高,除了第一个,其他的都被丢弃了
//            if(!limiter.tryAcquire()){
//                continue;
//            }
            new Thread(new Task()).start();
        }


    }
}
