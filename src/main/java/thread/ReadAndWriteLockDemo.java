package thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author happy
 */
public class ReadAndWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();

    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read-->" + value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(2000);
            value = index;
            System.out.println("write-->" + value);
        } finally {
            lock.unlock();
        }
    }

    /***
     * 结果：使用lock
     *       全程资源都是互斥,一个一个的读取，打印
     *       使用readAndWriteLock,读取可以同时读取,打印时一个一个占用资源
     * ***/
    public static void main(String[] args) {
        final ReadAndWriteLockDemo readAndWriteLockDemo = new ReadAndWriteLockDemo();
        Runnable readRunnable = () -> {
            try {
                readAndWriteLockDemo.handleRead(lock);
                // readAndWriteLockDemo.handleRead(readLock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {
            try {
                readAndWriteLockDemo.handleWrite(lock, new Random().nextInt());
                //readAndWriteLockDemo.handleWrite(writeLock, new Random().nextInt());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(readRunnable).start();
        }
        for (int i = 0; i < 20; i++) {
            new Thread(writeRunnable).start();
        }

    }


}
