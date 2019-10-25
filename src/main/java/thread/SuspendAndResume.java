package thread;

/**
 * @author happy
 * suspend and resume
 */
public class SuspendAndResume {

    final static Object ob = new Object();

    public static class T1 extends Thread {
        public T1() {
            super();
        }

        public T1(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (ob) {
                System.out.println(Thread.currentThread() + ":" + System.currentTimeMillis());
                Thread.currentThread().suspend();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        T1 thread1 = new T1("t1");
        T1 thread2 = new T1("t2");

        thread2.start();
        //Thread.sleep()睡的是主线程
        /**
         * 若是不睡  线程中的挂起  没有主线程中的释放快
         * 线程会无线挂起
         * 由于suspend 挂起 不会释放资源锁 ,其他线程将一直等待
         **/

        //System.out.println(Thread.currentThread() + ":" + System.currentTimeMillis());
        thread1.start();
        Thread.sleep(10000);
        thread1.resume();

        System.out.println(Thread.currentThread() + ":aaa" + System.currentTimeMillis());
        Thread.sleep(10000);
        thread2.resume();
        thread1.join();
        thread2.join();
        System.out.println(Thread.currentThread() + ":bbb" + System.currentTimeMillis());

    }

}
