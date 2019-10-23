package currentpkg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 需求：模拟一个厕所有3个坑，现在有10个人想上厕所，那么首先会有三个人进去，其他人都在外面等着，然后，三个人中有任何人先出来，那么马上又有人进去...
 * @author wb-gaoy
 * @version $Id: MySemaphore.java,v 0.1 2012-1-4 下午3:30:03 wb-gaoy Exp $
 */
public class MySemaphore {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        //构造三个坑
        final Semaphore semaphore = new Semaphore(3);
        //构造十个人
        final int count = 20;
        for (int i = 0; i < count; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("线程" + Thread.currentThread().getName() + "进入，当前 还有"
                                + semaphore.availablePermits() + "个空坑..");

                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("线程" + Thread.currentThread().getName() + "离开，当前 还有"
                                + (semaphore.availablePermits() + 1) + "个空坑..");
                        semaphore.release();
                    }
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
