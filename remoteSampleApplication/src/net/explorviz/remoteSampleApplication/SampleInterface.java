package net.explorviz.remoteSampleApplication;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Sample RMI interface for testing the remote call instrumentation aspects
 * 
 * @author Christian Zirkelbach (czi@informatik.uni-kiel.de)
 *
 */
public interface SampleInterface extends Remote {

	public String sayHello(String name) throws RemoteException;
}