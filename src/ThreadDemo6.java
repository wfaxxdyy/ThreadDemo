import java.util.concurrent.*;


//线程池的好处：
//（1）来创建线程比较消耗资源，不用重复创建；
//（2）池子事先定义好，避免无节制创建线程，导致系统出现不可预测风险。

//创建方式new ThreadPoolExecutor（参数）
//核心参数：corePoolSize、maximumPoolSize、keepAliveTime、unit、
// workQueue(Array有限队列,List无限队列max失效,Synchronous无缓存队列达到max拒绝,Priority根据比较器优先队列)、
// threadFactory、
// handler：CallerRunsPolicy直接运行被拒绝的run方法，AbortPolicy丢弃并抛RejectedExecutionException异常，DiscardPolicy丢弃最新的任务，DiscardOldestPolicy丢最老的任务

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

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        //创建线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 1000, TimeUnit.MICROSECONDS
                , arrayBlockingQueue, discardPolicy);

        //提交任务
        for (int i = 0; i <20 ; i++) {
            pool.submit(myCallable2);
        }
        //关闭线程池
        pool.shutdown();

    }

}

class MyCallable2 implements Callable {

    private int i = 200;

    @Override
    public Object call() throws Exception {

        while (i > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ":" + i--);
            }
        }
        return null;
    }
}
