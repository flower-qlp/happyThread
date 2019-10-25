package thread;

import java.util.Vector;

/**
 * @author happy
 * 守护线程
 */
public class Deamon {

    public static class myThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("-----------------------");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new myThread();
        //守护线程 需要在start()之前设置
        //运行结束  线程也自动结束
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2000);

    }

}
