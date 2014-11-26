package asfaleia;

public class ArrayUtils {
	public static byte[] concat(byte[]... args) {
		int fulllength = 0;
		for (byte[] arrItem : args) {
			fulllength += arrItem.length;
		}

		byte[] retArray = new byte[fulllength];
		int start = 0;
		for (byte[] arrItem : args) {
			System.arraycopy(arrItem, 0, retArray, start, arrItem.length);
			start += arrItem.length;
		}
		return retArray;
	}

	public static byte[] intToByteArray(int value) {
	    byte[] data = new byte[4];
	    
	    // int -> byte[]
	    for (int i = 0; i < 4; ++i)
	    {
	        int shift = i << 3; // i * 8
	        data[3 - i] = (byte) ((value & (0xff << shift)) >>> shift);
	    }
	    return data;
	}

	public static int byteArrayToInt(byte[] data) {
		// byte[] -> int
		int number = 0;
		for (int i = 0; i < 4; ++i) {
			number |= (data[3 - i] & 0xff) << (i << 3);
		}
		return number;
	}

	public static String bytes2String(byte[] bytes) {
	    StringBuilder string = new StringBuilder();
	    for (byte b : bytes) {
	        String hexString = Integer.toHexString(0x00FF & b);
	        string.append(hexString.length() == 1 ? "0" + hexString : hexString);
	    }
	    return string.toString();
	}





}




