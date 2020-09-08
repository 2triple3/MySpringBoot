package remote.procedure.call.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxyObject(final Class serviceInterface,final InetSocketAddress addr){
		return (T) Proxy.newProxyInstance(
				serviceInterface.getClassLoader(), 
				new Class<?>[]{serviceInterface},
				new InvocationHandler() {
					
			    public Object invoke(Object proxy, Method method, Object[] args){
				    ObjectOutputStream output = null;
				    ObjectInputStream input = null;
					try{
						Socket socket = new Socket();
						socket.connect(addr);
						output = new ObjectOutputStream(socket.getOutputStream());
						output.writeUTF(serviceInterface.getName());
						output.writeUTF(method.getName());
						output.writeObject(method.getParameterTypes());
						output.writeObject(args);
						input = new ObjectInputStream(socket.getInputStream());
						return input.readObject();
					}catch(Exception e){
						return null;
					}finally{
						try {
							if(input!=null)input.close();
							if(output!=null)output.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}

		});
		
	}

	
}
