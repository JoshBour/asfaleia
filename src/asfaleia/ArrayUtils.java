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
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16),
				(byte) (value >>> 8), (byte) value };
	}






}




