package remote.procedure.call.server;


public class HelloServiceImpl implements HelloService {

	public String sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("服务端HelloServiceImpl:"+"Hello "+name);
		return "Hello " + name;

	}

}
