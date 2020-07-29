//演示死锁
//解决方案：避免锁嵌套，注意锁顺序
public class ThreadDemo8 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final Object lock1 = new Object(), lock2 = new Object();
        new Thread(new Runnable() {
            public void run() {
                synchronized (lock1) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread1 get t1");
                    synchronized (lock2) {
                        for (int i = 1; i <= 100; ++i)
                            System.out.println(Thread.currentThread().getName());
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                synchronized (lock2) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread2 get t2");
                    synchronized (lock1) {
                        for (int i = 1; i <= 100; ++i)
                            System.out.println(Thread.currentThread().getName());
                    }
                }
            }
        }).start();

    }
}

