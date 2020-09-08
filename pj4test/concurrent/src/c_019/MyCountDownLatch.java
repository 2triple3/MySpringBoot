package c_019;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch c = new CountDownLatch(1);

        new Thread(
                ()->{
                    System.out.println("thread1启动");
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread1结束");
                }
                ,"thread1"
        ).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(
                ()->{
                    System.out.println("thread2启动");
                    c.countDown();
                    System.out.println("thread2开门");
                    System.out.println("thread2停止");
                }
                ,"thread2"
        ).start();

    }

}
