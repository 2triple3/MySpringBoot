package c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock1 {

    public static void main(String[] args) {
        MyReentrantLock1 r1 = new MyReentrantLock1();
        new Thread(r1::m1).start();
        new Thread(r1::m2).start();

        //System.out.println();
    }

    Lock lock = new ReentrantLock();

    void m1(){
        try{
            lock.lock();
            System.out.println("m1...");
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        lock.lock();
        System.out.println("m2...");
        lock.unlock();
    }

}
