package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch cdl = new CountDownLatch(3);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                function1();
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                function2();
                cdl.countDown();
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                function3();
                cdl.countDown();
            }
        });

        try {
            cdl.await();
            System.out.println("三个执行线程结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("执行线程异常");
        }
        finally {
            executorService.shutdown();
            System.out.println("执行线程关闭");
        }
    }

    private static void function1(){
//        int i = 10/0;
        System.out.println("方法一");
    }

    private static void function2(){
        System.out.println("方法二");
    }

    private static void function3(){
        System.out.println("方法三");
    }
}
