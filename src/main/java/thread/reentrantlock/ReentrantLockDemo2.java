package thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 等待锁的使用
 * tryLock()
 *
 * @author happy
 **/
public class ReentrantLockDemo2 implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "尝试获取资源");
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "获取资源");
                Thread.sleep(6000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "尝试释放资源");
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放资源");
            }
        }
    }

    public static void main(String[] args) {
        /**
         *俩者相争 线程1拿到资源 睡6秒
         *线程2没拿到,等待5秒，没有获取资源,2结束
         * 线程1完成 释放资源
         **/

        ReentrantLockDemo2 timeLock = new ReentrantLockDemo2();
        Thread thread1 = new Thread(timeLock);
        Thread thread2 = new Thread(timeLock);
        thread1.start();
        thread2.start();
    }
}
