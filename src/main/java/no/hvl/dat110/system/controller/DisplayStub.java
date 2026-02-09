package no.hvl.dat110.system.controller;

import java.io.IOException;

import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	// implement marshalling, call and unmarshalling for write RPC method
	public void write (String message) throws IOException {
		
		byte[] param = RPCUtils.marshallString(message);
		byte[] reply = rpcclient.call((byte)Common.WRITE_RPCID, param);
	    RPCUtils.unmarshallVoid(reply);
	}
}
