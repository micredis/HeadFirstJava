import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.rmi.registry.*;

// A normal RMI implementation
public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {
	
	HashMap serviceList;

	public ServiceServerImpl() throws RemoteException {
		setUpServices();
	}

	private void setUpServices() {
		serviceList = new HashMap();
		serviceList.put("Dice Rolling Service", new DiceService());
		serviceList.put("Day of the Week Service", new DayOfTheWeekService());
		serviceList.put("Visual Music Service", new MiniMusicService());
	}

	public Object[] getServiceList() {
		System.out.println("in remote");
		// Client calls this in order to get a list of services to display in the
		// browser (so the user can select one). We send an array of type Object
		// (even though it has Strings inside) by making an array of just the KEYS
		// that are in the HashMap. We won't send an actual Service object unless
		// the client asks for it by calling getService().
		return serviceList.keySet().toArray();
	}

	public Service getService(Object serviceKey) throws RemoteException {
		Service theService = (Service) serviceList.get(serviceKey);
		return theService;
	}

	public static void main(String[] args) {
		try {
			//Registry registry = LocateRegistry.getRegistry();
			//registry.bind("ServiceServer", new ServiceServerImpl());
			Naming.rebind("ServiceServer", new ServiceServerImpl());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Remote service is running");
	}
}