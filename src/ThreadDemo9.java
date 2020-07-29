
//测试ThreadLocal（同步每一个线程的各自的变量）
public class ThreadDemo9 {

    public static void main(String[] args) {

        new Thread(() -> {
            thread1();
        }).start();
        new Thread(() -> {
            thread2();
        }).start();

    }

    private static void thread1() {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("张三");
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "_" + i + ":" + threadLocal.get());
            }
        } finally {
            threadLocal.remove();
        }


    }

    private static void thread2() {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("李四");
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "_" + i + ":" + threadLocal.get());
            }
        } finally {
            threadLocal.remove();
        }

    }
}
