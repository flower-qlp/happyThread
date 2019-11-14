package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.SimpleFormatter;

/**
 * ThreadLocal 人手一只笔
 **/
public class ThreadLocalDemo {
    /**
     * 为每个线程创建独立的simpleDateFormat
     * 但是 不保证线程安全,需要在应用层面上新建对象
     **/
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-HH-dd");
    private static ThreadLocal<SimpleDateFormat> th = new ThreadLocal<>();

    public static class myTask implements Runnable {
        int index;

        public myTask(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            try {

                if (th.get() == null) {
                    th.set(new SimpleDateFormat("yyyy-MM-dd"));
                }
//              Date date=th.get().parse("2019-15-"+index);
                Date date = dateFormat.parse("2019-15-" + index);
                System.out.println("结果：" + date.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //SimpleDateFormat 是线程不安全的,有可能会出现异常 方法:可以加锁
        //方法2：threadLocal
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.execute(new myTask(i));
        }
        es.shutdown();
    }

}
