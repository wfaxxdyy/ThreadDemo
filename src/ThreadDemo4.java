import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


//使用callable实现多线程(同步代码不起作用！！！可能是FutureTask自动加锁的原因，目前也不确定！)
public class ThreadDemo4 {

    public static void main(String[] args) {

        FutureTask futureTask1 = new FutureTask<>(new MyCallable());
        Thread thread1 = new Thread(futureTask1);
        Thread thread2 = new Thread(futureTask1);
        thread1.start();
        thread2.start();
    }

}

class MyCallable implements Callable {

    private int i = 20;

    @Override
    public Object call() throws Exception {

        while (i > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + ":" + i--);
            }
        }
        return null;
    }
}
