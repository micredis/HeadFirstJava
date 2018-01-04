import java.rmi.*;

// A normal RMI remote interface, defines the two methods the remote
// service will have.

public interface ServiceServer extends Remote {
	Object[] getServiceList() throws RemoteException;
	Service getService(Object serviceKey) throws RemoteException;
}