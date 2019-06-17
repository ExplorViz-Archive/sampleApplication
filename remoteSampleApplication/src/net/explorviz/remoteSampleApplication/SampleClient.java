package net.explorviz.remoteSampleApplication;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class SampleClient {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		int minNumber = 1;
		int maxNumber = 100;
		Random randNumber = new Random();
		int randomNumber = minNumber + randNumber.nextInt(maxNumber);
		String passedName = "Client with id " + randomNumber;

		try {
			Registry registry = LocateRegistry.getRegistry();
			SampleInterface rmiServer = (SampleInterface) registry.lookup("RMIServer");
			System.out.println("Response: " + rmiServer.sayHello(passedName));
		} catch (Exception e) {
			System.err.println("Client got exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
