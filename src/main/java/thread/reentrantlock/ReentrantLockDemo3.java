package thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author happy
 * trylock()避免死锁
 **/
public class ReentrantLockDemo3 implements Runnable {

    public static ReentrantLock reentrantLock1 = new ReentrantLock();
    public static ReentrantLock reentrantLock2 = new ReentrantLock();
    int lock;

    public ReentrantLockDemo3(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (reentrantLock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        if (reentrantLock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + "获取到锁 reentrantLock2");
                                return;
                            } finally {
                                reentrantLock2.unlock();
                            }
                        }
                    } finally {
                        reentrantLock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if (reentrantLock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        if (reentrantLock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + "获取到锁 reentrantLock1");
                                return;
                            } finally {
                                reentrantLock1.unlock();
                            }
                        }
                    } finally {
                        reentrantLock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo3 r1 = new ReentrantLockDemo3(1);
        ReentrantLockDemo3 r2 = new ReentrantLockDemo3(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
