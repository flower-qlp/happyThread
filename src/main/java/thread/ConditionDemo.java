package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**t
*和重入锁ReentrantLock连用
*@author happy
 **/
public class ConditionDemo implements Runnable {

    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition=lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("the thread is lock 。。。。"+System.currentTimeMillis());
            condition.await();
            System.out.println("the thread is going on 。。。。"+System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo conditionDemo=new ConditionDemo();
        Thread t1=new Thread(conditionDemo);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        //唤醒线程 需要给它资源
        condition.signal();
        //释放当前的资源 给唤醒的线程  如果不是放,唤醒的线程也会在一直等待
        lock.unlock();
    }
}


