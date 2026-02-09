package no.hvl.dat110.rpc;

import java.io.IOException;
import java.net.UnknownHostException;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	// connect using the RPC client
	public void connect() throws UnknownHostException, IOException {
		
		connection = msgclient.connect();
	}
	
	// disconnect by closing the underlying messaging connection
	public void disconnect() {
		
		connection.close();
	}

	/*
	 Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) throws IOException {
		
		byte[] rpcmsg = RPCUtils.encapsulate(rpcid, param);
		Message msgSend = new Message(rpcmsg);
		connection.send(msgSend);
		Message msgReceive = connection.receive();
		byte[] returnval = RPCUtils.decapsulate(msgReceive.getData());
		
		// TODO - END
		return returnval;
		
	}

}
