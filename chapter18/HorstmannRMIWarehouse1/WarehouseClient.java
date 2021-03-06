import java.rmi.*;
import java.util.*;
import javax.naming.*;

public class WarehouseClient {
	public static void main(String[] args) throws NamingException, RemoteException {
		Context namingContext = new InitialContext();

		System.out.println("RMI registry bindings: ");
		Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
		while (e.hasMoreElements())
			System.out.println(e.nextElement().getName());

		String url = "rmi://localhost/central_warehouse";
		Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);

		String descr = "Blackwell Toaster";
		double price = centralWarehouse.getPrice(descr);
		System.out.println(descr + ": " + price);
	}
}