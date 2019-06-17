package net.explorviz.remoteSampleApplication;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SampleInterface extends Remote {

	public String sayHello(String name) throws RemoteException;
}
