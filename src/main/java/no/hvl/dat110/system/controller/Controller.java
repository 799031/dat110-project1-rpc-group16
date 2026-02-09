package no.hvl.dat110.system.controller;

import java.io.IOException;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) throws IOException {
				
		System.out.println("Controller starting ...");
				
		// create RPC clients for the system
		RPCClient displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		RPCClient sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		
		// setup stop methods in the RPC middleware
		RPCClientStopStub stopdisplay = new RPCClientStopStub(displayclient);
		RPCClientStopStub stopsensor = new RPCClientStopStub(sensorclient);
						
		// create local display and sensor stub objects
		DisplayStub display = new DisplayStub(displayclient);
		SensorStub sensor = new SensorStub(sensorclient);

		// connect to sensor and display RPC servers - using the RPCClients
		displayclient.connect();
		sensorclient.connect();
		
		for(int i=0; i<N; i++) {
			// read value from sensor using RPC and write to display using RPC
			int temp = sensor.read();
			display.write(String.valueOf(temp));
		}
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
