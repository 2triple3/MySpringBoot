import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        Person coder = new Coder();
        CoderProxyHandler cph = new CoderProxyHandler(coder);
        Person coderProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, cph);
        coderProxy.eat();
    }

    public interface Person{
        public void eat();
    }

    public static class Coder implements Person{

        public void eat() {
            System.out.println("I'm eatting");
        }
    }

    public static class CoderProxyHandler implements InvocationHandler{

        private Person person;

        public CoderProxyHandler(Person person) {
            this.person = person;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before eatting");
            Object obj = method.invoke(person,args);
            System.out.println("after eatting");
            return obj;
        }
    }
}
