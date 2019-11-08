package thread.threadpool;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分而治之fork/join框架
 * RecursiveTask返回结果
 *
 * @author happy
 **/
public class RecursiveTaskDemo extends RecursiveTask<Long> {

    private static final int threshold = 21;

    private long start;

    private long end;

    public RecursiveTaskDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
//        切分任务对象,该实例中将任务切分为5份
        long sum = 0;
        boolean canCompute = (end - start) < threshold;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long step = (start + end) / 5;
            ArrayList<RecursiveTaskDemo> tasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 5; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                System.out.println("pos=" + pos + " lastOne=" + lastOne);
//                任务对象列表
                RecursiveTaskDemo demo = new RecursiveTaskDemo(pos, lastOne);
                pos += step + 1;
                tasks.add(demo);
//               提交子任务
                demo.fork();
            }

            for (RecursiveTaskDemo task : tasks) {
//                子任务完成,计算--完成线程
                sum += task.join();
                System.out.println("num=" + task.join());
            }

        }
        return sum;
    }

    public static void main(String[] args) {
//        新建线程池
//        新建线程对象
//        运行线程对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveTaskDemo taskDemo = new RecursiveTaskDemo(0, 100);
        ForkJoinTask<Long> result = forkJoinPool.submit(taskDemo);
        try {
            long res = result.get();
            System.out.println("sum=" + res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
