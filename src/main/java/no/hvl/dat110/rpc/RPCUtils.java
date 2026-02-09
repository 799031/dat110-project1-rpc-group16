package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		if(payload == null) {
			payload = new byte[0];
		}
		
		byte[] rpcmsg = new byte[payload.length + 1];
		
		rpcmsg[0] = rpcid;
		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		if (rpcmsg == null || rpcmsg.length < 1) {
	        throw new IllegalArgumentException();
	    }
		
		byte[] payload = Arrays.copyOfRange(rpcmsg, 1, rpcmsg.length);
		
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = str.getBytes(StandardCharsets.UTF_8);
				
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = new String(data, StandardCharsets.UTF_8); 
				
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {

	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
		
		encoded[0] = (byte) (b ? 1 : 0);
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = ByteBuffer.allocate(Integer.BYTES).putInt(x).array();
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = ByteBuffer.wrap(data).getInt();
		
		return decoded;
		
	}
}
