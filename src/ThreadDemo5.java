import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


//使用callable实现多线程,以及线程池的使用
public class ThreadDemo5 {

    public static void main(String[] args) {

        MyCallable1 myCallable1 = new MyCallable1();

        //创建线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(2);

        //提交任务
        pool.submit(myCallable1);
        pool.submit(myCallable1);

        //关闭线程池
        pool.shutdown();

    }

}

class MyCallable1 implements Callable {

    private int i = 20;

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
