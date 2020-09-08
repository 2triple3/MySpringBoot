import java.util.concurrent.CountDownLatch;

public class WaterMachine {
    public static void main(String[] args) throws Exception {
        final WaterMachine waterMachine = new WaterMachine();

        /**
         * 早上刚上班，5 个人都去接水
         */
        for (int i = 0; i < 5; i++) {
             final CountDownLatch countDownLatch = new CountDownLatch(1);

             new Thread("#Staff" + i) {
                @Override
                public void run() {
                    super.run();
                    try {

                        waterMachine.loadWater();
                        countDownLatch.countDown();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
//             Thread  thread = new Thread("t1"){
//                 public void run(){}
//             };
//            thread.start();
            countDownLatch.await();
        }


    }

    public void loadWater() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " enter");
        System.out.println(Thread.currentThread().getName() + " 正在接水...");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " exit");
    }
}