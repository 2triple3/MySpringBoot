public class CountdownLatchTest {


    public static void main(String[] args) throws InterruptedException {
        Object o =new Object();
        for(int i=0;i<5;i++){
            synchronized (o){
                new Thread("#Staff" + i){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            loadWater();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }

    public static synchronized void  loadWater() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " enter");
        System.out.println(Thread.currentThread().getName() + " 正在接水...");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " exit");
    }

}
