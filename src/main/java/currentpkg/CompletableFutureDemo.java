package currentpkg;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureDemo {
    public Future<Double> getPriceAsync(String product) {
        //创建CompletableFuture对象
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread (() -> {
            try {
                //在另一个线程中执行计算
                double price = getPrice(product);
                //需要长时间计算的任务结束并得出结果时，设置future的返回值
                futurePrice.complete(price);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return futurePrice;
    }
    public Double getPrice(String product) throws InterruptedException {
        //查询商品的数据库，或链接其他外部服务获取折扣
        Thread.sleep(1000);
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsyncExt(String product) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getPrice(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureDemo shop = new CompletableFutureDemo();
        System.out.println("begin");
        Future<Double> futurePrice = shop.getPriceAsync("ss");

        System.out.println("doSomething");
        System.out.println(futurePrice.get());
        System.out.println("end");
    }
}