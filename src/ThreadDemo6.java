import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//线程池的好处：
//（1）来创建线程比较消耗资源，不用重复创建；
//（2）池子事先定义好，避免无节制创建线程，导致系统出现不可预测风险。

//创建方式new ThreadPoolExecutor（参数）
//核心参数：corePoolSize、maximumPoolSize、keepAliveTime、unit、workQueue、threadFactory、handler

//创建
//工具类 : Executors
// * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
// * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
// * ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
// * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
// */

public class ThreadDemo6 {

    public static void main(String[] args) {

        MyCallable2 myCallable2 = new MyCallable2();

        //创建线程池对象
        ExecutorService pool = Executors.newSingleThreadExecutor();

        //提交任务
        pool.submit(myCallable2);

        //关闭线程池
        pool.shutdown();

    }

}

class MyCallable2 implements Callable {

    private int i = 20;

    @Override
    public Object call() throws Exception {

        while (i > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i--);

        }
        return null;
    }
}
