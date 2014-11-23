package asfaleia;

import java.security.KeyPair;
import java.security.Signature;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			KeyPair pair = KeyGenerator.generateKeyPair();
			String message = "Test";
			
			
			byte[] signedMessage = new byte[0];
			
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initSign(pair.getPrivate());
			sig.update(message.getBytes());
			byte[] signature = sig.sign();
			
	        signedMessage = concat(signedMessage,intToByteArray(signature.length));
	        signedMessage = concat(signedMessage,signature);

//			return concat(signedMessage,message.getBytes());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Converts a int value into a byte array.
	 *
	 * @param value  int value to be converted
	 * @return  byte array containing the int value
	 */
	private static byte[] intToByteArray(int value)
	{
	    byte[] data = new byte[4];
	 
	    // int -> byte[]
	    for (int i = 0; i < 4; ++i)
	    {
	        int shift = i << 3; // i * 8
	        data[3 - i] = (byte) ((value & (0xff << shift)) >>> shift);
	    }
	    return data;
	}
	 
	/**
	 * Converts a byte array to an int value.
	 *
	 * @param data  byte array to be converted
	 * @return  int value of the byte array
	 */
	private static int byteArrayToInt(byte[] data)
	{
	    // byte[] -> int
	    int number = 0;
	    for (int i = 0; i < 4; ++i)
	    {
	        number |= (data[3-i] & 0xff) << (i << 3);
	    }
	    return number;
	}
	
	/**
	 * Concatenates two byte arrays and returns the resulting byte array.
	 *
	 * @param a  first byte array
	 * @param b  second byte array
	 * @return  byte array containing first and second byte array
	 */
	private static byte[] concat(byte[] a, byte[] b)
	{
	    byte[] c = new byte[a.length + b.length];
	    System.arraycopy(a, 0, c, 0, a.length);
	    System.arraycopy(b, 0, c, a.length, b.length);
	 
	    return c;
	}
}
