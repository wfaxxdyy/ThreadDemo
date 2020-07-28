
//使用synchronized进行同步,注意同步只需加在待同步的数据上，不要随意使用。
public class ThreadDemo1 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        thread1.start();
        thread2.start();
    }

}

class MyRunnable implements Runnable {
    private int i = 20;

    @Override
    public void run() {

        while (i > 0) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ":" + i--);
            }
        }
    }
}