package remote.procedure.call.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerImpl implements Server {
	private static HashMap<String,Class> serviceRegister = new HashMap();
	private static int port;
	private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private boolean isRunning = false; 
    public ServerImpl(int port){
    	this.port = port;
    }
	public void serviceRegister(Class service,Class serviceImpl) {
		// TODO Auto-generated method stub
		serviceRegister.put(service.getName(),serviceImpl);

	}

	@SuppressWarnings("unchecked")
	public void start()   {
		System.out.println("服务启动。。。");
		ServerSocket serverSocket =null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(port));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		isRunning = true;
           while(true){
        	   Socket socket = null;
				try {
					socket = serverSocket.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	   executor.execute(new ServerTask(socket));
           }
	}

	public void stop() {
		isRunning = false;
		executor.shutdown();
	}
	
	private static class ServerTask implements Runnable{
		private Socket socket;
		public ServerTask(){}
		public ServerTask(Socket socket){
			this.socket=socket;
		}

		public void run() {
			ObjectInputStream input = null;
			ObjectOutputStream output = null;	
			try{
				// TODO Auto-generated method stub
//				ServerSocket serverSocket =new ServerSocket();
//				serverSocket.bind(new InetSocketAddress(port));
//				Socket socket = serverSocket.accept();
			    input = new ObjectInputStream(socket.getInputStream());
				String serviceName = input.readUTF();
				String methodName = input.readUTF();
				Class[] parameterTypes = (Class[]) input.readObject();
				Object[] arguments = (Object[]) input.readObject();
				Class serviceClass = serviceRegister.get(serviceName);
				Method method = serviceClass.getMethod(methodName, parameterTypes);
				Object result = method.invoke(serviceClass.newInstance(), arguments);
				output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			finally{
					try {
						if(input!=null)input.close();
						if(output!=null)output.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}		
	}

}
