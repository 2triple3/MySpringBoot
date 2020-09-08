package c_001;

public class DCLSingleton {
    private volatile static  DCLSingleton dclSingleton;
    private DCLSingleton(){}
    public static  DCLSingleton getInstance(){
        if(dclSingleton==null){
            synchronized (DCLSingleton.class){
                if(dclSingleton==null){
                    dclSingleton = new DCLSingleton();
                }
            }
        }
        return dclSingleton;
    }
}
