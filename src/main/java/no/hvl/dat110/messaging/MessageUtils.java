package no.hvl.dat110.messaging;

import java.util.Arrays;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	// encapulate/encode the payload data of the message and form a segment
	// according to the segment format for the messaging layer
	public static byte[] encapsulate(Message message) {
		
		if (message == null) {
			throw new IllegalArgumentException();
		}

		byte[] segment = new byte[SEGMENTSIZE];
		byte[] data = message.getData();
		
		byte length = (byte) data.length;
		segment[0] = length;
		for(int i=0; i<length; i++) {
			segment[i + 1] = data[i];
		}
		
		return segment;
	}

	// decapsulate segment and put received payload data into a message
	public static Message decapsulate(byte[] segment) {

		if (segment.length != SEGMENTSIZE) {
			throw new IllegalArgumentException();
		}
		
		int length = Byte.toUnsignedInt(segment[0]);
		byte[] data = Arrays.copyOfRange(segment, 1, length + 1);
		Message message = new Message(data);
		
		return message;
	}
	
}
