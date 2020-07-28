
//使用volatile进行同步
public class ThreadDemo2 {
    public static void main(String[] args) {
        MyRunnable1 myRunnable = new MyRunnable1();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        thread1.start();
        thread2.start();
    }

}
class MyRunnable1 implements Runnable{
    //不能保证安全性，因为不是原子操作
    private volatile int i=20;
    @Override
    public void run() {

            while(i>0){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":"+i--);
            }




    }
}