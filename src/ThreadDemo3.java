import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//使用lock进行同步
public class ThreadDemo3 {
    public static void main(String[] args) {
        MyRunnable2 myRunnable = new MyRunnable2();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        thread1.start();
        thread2.start();
    }

}

class MyRunnable2 implements Runnable {

    private Lock lock = new ReentrantLock();

    private int i = 20;

    @Override
    public void run() {

        while (i > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ":" + i--);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }


    }
}