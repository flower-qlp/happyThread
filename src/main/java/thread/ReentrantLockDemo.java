package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author itoutsource.cz10
 * synchronized的扩展---重入锁
 * 重入锁:同一个线程可以多次获取锁,释放时也必须释放相同数量的锁
 */
public class ReentrantLockDemo implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            lock.lock();
            try {
                i++;
                System.out.println(Thread.currentThread());
            } finally {
                lock.unlock();
            }
        }
    }
}
