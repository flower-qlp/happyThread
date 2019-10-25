package thread;

/**
 * @author happy
 * wait and notify
 */
public class WaitAndNotify {

    final static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("thread1 start : " + System.currentTimeMillis());
                try {
                    object.wait();   //会放弃当前的对象的锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 end : " + System.currentTimeMillis());
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("thread2 start : " + System.currentTimeMillis());
                object.notify();
                try {
                    //休眠 不会放弃当前的对象锁
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 end : " + System.currentTimeMillis());
            }
        }
    }


    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
