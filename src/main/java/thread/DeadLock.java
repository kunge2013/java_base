package thread;

/**
 * 死锁
 */
public class DeadLock {
    public static String A = "a";
    public static String B = "b";

    public static void main(String[] args) {
        new DeadLock().deadLock();
    }

    public void deadLock()  {
        Thread a = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.currentThread().sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("thread a lock .....");
                }
            }
        });

        Thread b = new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.currentThread().sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("thread b lock .....");
                }
            }
        });
        a.start();
        b.start();
    }
}
