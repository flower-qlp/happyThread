package thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 中断响应
 * lock and unlock
 * @author happy
 * **/
public class ReentrantLockDemo1 implements Runnable {
    public static ReentrantLock lock1=new ReentrantLock();
    public static ReentrantLock lock2=new ReentrantLock();
    int lock;


    public ReentrantLockDemo1(int lock){
         this.lock=lock;
    }

    @Override
    public void run() {
      try {
          if(lock==1){
              lock1.lockInterruptibly();
              try {
                  Thread.sleep(2000);
              }catch (Exception e){
                  e.printStackTrace();
              }
              lock2.lockInterruptibly();
          }else {
              lock2.lockInterruptibly();
              try {
                  Thread.sleep(2000);
              }catch (Exception e){e.printStackTrace();}
              lock1.lockInterruptibly();
          }

      }catch (Exception e){
          e.printStackTrace();
      }finally {
          if(lock1.isHeldByCurrentThread()){
              lock1.unlock();
          }
          if(lock2.isHeldByCurrentThread()){
              lock2.unlock();
          }
          System.out.println(Thread.currentThread().getId());
      }
    }

    public static void main(String[] args) throws InterruptedException {
        /**lock1和lock2互相等待 直到lock2中断的申请**/
        ReentrantLockDemo1 reentLock1=new ReentrantLockDemo1(1);
        ReentrantLockDemo1 reentLock2=new ReentrantLockDemo1(2);
        Thread th1=new Thread(reentLock1);
        Thread th2=new Thread(reentLock2);
        th1.start();
        th2.start();
        Thread.sleep(5000);
        th2.interrupt();

    }
}
