package remote.procedure.call.server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Server {
	public void start();
	public void stop();
	public void serviceRegister(Class service,Class serviceImpl);

}
