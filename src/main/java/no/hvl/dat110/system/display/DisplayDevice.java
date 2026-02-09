package no.hvl.dat110.system.display;

import java.io.IOException;

import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;


public class DisplayDevice {
	
	// implement the operation of the display RPC server
	// see how this is done for the sensor RPC server in SensorDevice
	public static void main(String[] args) throws IOException {
		
		System.out.println("Display server starting ...");
		
		try {
		RPCServer rpcserver = new RPCServer(Common.DISPLAYPORT);
		DisplayImpl displayImpl = new DisplayImpl((byte)Common.WRITE_RPCID, rpcserver);
		rpcserver.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Display server stopping ...");
		
	}
}
