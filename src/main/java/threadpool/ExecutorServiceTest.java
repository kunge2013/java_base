package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 具体的4种常用的线程池实现如下：（返回值都是ExecutorService）
 */
public class ExecutorServiceTest {
    /**
     * Executors.newCacheThreadPool()：可缓存线程池，先查看池中有没有以前建立的线程，
     * 如果有，就直接使用。如果没有，就建一个新的线程加入池中，
     * 缓存型池子通常用于执行一些生存期很短的异步型任务
     */
    public static void ThreadPoolExecutorTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            try {
                //sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    //打印正在执行的缓存线程信息
                    System.out.println(Thread.currentThread().getName() + "正在被执行");
                }
            });
        }
    }


    /**
     * //创建一个可重用固定个数的线程池
     */
    public static void newFixedThreadPoolTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        //打印正在执行的缓存线程信息
                        System.out.println(Thread.currentThread().getName() + "正在被执行");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
     */
    public static void newScheduledThreadPoolTest() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("延迟1秒执行");
            }
        }, 1, TimeUnit.SECONDS);
    }

    /**
     * //创建一个定长线程池，支持定时及周期性任务执行——定期执行
     * //延迟1秒后每3秒执行一次
     */
    public static void newScheduledThreadPoolTimerTest() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("延迟1秒执行");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * Executors.newSingleThreadExecutor()：创建一个单线程化的线程池，
     * 它只会用唯一的工作线程来执行任务，
     * 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    public static void newSingleThreadExecutorTest() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        //结果依次输出，相当于顺序执行各个任务
                        System.out.println(Thread.currentThread().getName() + "正在被执行,打印的值是:" + index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        /**
         * Executors.newCacheThreadPool()：可缓存线程池，先查看池中有没有以前建立的线程，
         * 如果有，就直接使用。如果没有，就建一个新的线程加入池中，
         * 缓存型池子通常用于执行一些生存期很短的异步型任务
         */
        ThreadPoolExecutorTest();
        /**
         *  //创建一个可重用固定个数的线程池
         */
        newFixedThreadPoolTest();
        /**
         * //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
         */
        newScheduledThreadPoolTest();
        /**
         * //创建一个定长线程池，支持定时及周期性任务执行——定期执行
         * //延迟1秒后每3秒执行一次
         */
        newScheduledThreadPoolTimerTest();

        /**
         *  Executors.newSingleThreadExecutor()：创建一个单线程化的线程池，
         * 它只会用唯一的工作线程来执行任务，
         *  保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
         */
        newSingleThreadExecutorTest();
    }
}