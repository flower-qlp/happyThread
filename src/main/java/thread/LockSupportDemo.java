package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author happy
 */
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("通");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                try {
                    System.out.println("in " + getName());
                    Thread.sleep(4000);
                    LockSupport.park(this);

                    if (Thread.interrupted()) {
                        System.out.println(getName() + "被中断了！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * lockSupport使用的是类似于信号量的的方法
     * 但是他不会叠加,一个线程上就只能一个
     * 若park在unpark之后，unpark的许可证会使下一次的park通过
     **/
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        System.out.println("主线程 ");
        t2.start();
//        t1.interrupt();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();

    }
}
