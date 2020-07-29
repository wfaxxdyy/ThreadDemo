import java.util.concurrent.*;

//创建
//工具类 : Executors(建议看源码！！！)
// newFixedThreadPool：创建的是定长的线程池，可以控制线程最大并发数，超出的线程会在线程中等待，使用的是无界队列，核心线程数和最大线程数一样，
// 当线程池中的线程没有任务时候立刻销毁,使用默认线程工厂。
//
//newSingleThreadExecutor：创建的是单线程化的线程池，只会用唯一一个工作线程执行任务，可以指定按照是否是先入先出，还是优先级来执行任务。
// 同样使用无界队列，核心线程数和最大线程数都是1个，同样keepAliveTime为0，可选择是否使用默认线程工厂。
//
//newCachedThreadPool：设定一个可缓存的线程池，当线程池长度超过处理的需要，可以灵活回收空闲线程，如果没有可以回收的才新建线程。
// 没有核心线程数，当线程没有任务60s之后就会回收空闲线程，使用有界队列。同样可以选择是否使用默认线程工厂。
//
//newScheduledThreadPool：支持线程定时操作和周期性操作。
// */

public class ThreadDemo7 {

    public static void main(String[] args) {

        MyCallable3 myCallable3 = new MyCallable3();

        //创建线程池对象
        ExecutorService pool1 = Executors.newFixedThreadPool(5);

        //提交任务
        for (int i = 0; i <20 ; i++) {
            pool1.submit(myCallable3);
        }
        //关闭线程池
        pool1.shutdown();

    }

}

class MyCallable3 implements Callable {

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
