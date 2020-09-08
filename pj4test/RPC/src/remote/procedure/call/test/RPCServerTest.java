package remote.procedure.call.test;

import remote.procedure.call.server.HelloService;
import remote.procedure.call.server.HelloServiceImpl;
import remote.procedure.call.server.Server;
import remote.procedure.call.server.ServerImpl;

public class RPCServerTest {
	public static void main(String[] args){
		Server server = new ServerImpl(7002);
		server.serviceRegister(HelloService.class, HelloServiceImpl.class);
		server.start();
	}

}
