package net.explorviz.remoteSampleApplication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

/**
 * Sample RMI server for testing the remote call instrumentation aspects
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public class SampleServer implements SampleInterface {

	@Override
	public String sayHello(String name) throws RemoteException {
		System.err.println(name + " is trying to say hello!");
		return "Server says hello to " + name + " !";
	}

	public static void main(String[] args) throws RemoteException {

		try {
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

			SampleServer server = new SampleServer();
			SampleInterface stub = (SampleInterface) UnicastRemoteObject.exportObject(server, 0);
			RemoteServer.setLog(System.out);

			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("RMIServer", stub);

			System.out.println("RMIServer registred");
		} catch (Exception e) {
			System.err.println("Server got exception: " + e.toString());
			e.printStackTrace();
		}
	}
}