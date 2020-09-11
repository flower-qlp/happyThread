package thread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @author happy
 * 循环栏栅
 * 类似于countDownLatch ,可以循环使用
 * <p>
 * 模拟:等待所有士兵都到齐了一起执行任务,
 * 全部都之行完毕，才宣布完成任务
 */
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable {
        private String soldier;

        private final CyclicBarrier cyclicBarrier;

        public Soldier(CyclicBarrier cyclicBarrier, String soldier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有的士兵到齐
                cyclicBarrier.await();
                doWork();
                //等待所有的士兵全部完成工作
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(soldier + "任务完成");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N) {
            this.flag = flag;
            this.N = N;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：[士兵" + N + "个任务完成！]");
            } else {
                System.out.println("司令：[士兵" + N + "个集合完毕！]");
                flag = true;
            }
        }
    }


    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        /***定义要求达到的数量,以及达到之后需要干的事***/
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("队伍结合");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道！");
            allSoldier[i] = new Thread(new Soldier(cyclicBarrier, "士兵" + i));
            allSoldier[i].start();
//            会抛出一个线程中断异常InterruptedException以及9个BrokenBarrierException
//            中断是当前中断线程抛出,9个是其他线程无法等到10个线程完成抛出的,防止线程无线等待
//            if (i == 5) {
//                allSoldier[i].interrupt();
//            }
        }
    }

}
