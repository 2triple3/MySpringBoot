package remote.procedure.call.test;

import java.net.InetSocketAddress;

import remote.procedure.call.client.Client;
//import remote.procedure.call.client.HelloService;
//RPC实际中难道客户端需要有跟服务端一致的服务包结构？
import remote.procedure.call.server.HelloService;
//import remote.procedure.call.server.HelloServiceImpl;
//import remote.procedure.call.server.ServerImpl;

public class RPCClientTest {
	public static void main(String[] args) throws ClassNotFoundException{
    HelloService service = Client.getRemoteProxyObject(Class.forName("remote.procedure.call.server.HelloService"), new InetSocketAddress("127.0.0.1", 7002));
    System.out.println("客户端："+service.sayHello("666"));
	}
}
