package asfaleia;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SecurityUtils {
	public static byte[] signMessage(byte[] message, PrivateKey key) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException{
		Signature sig = Signature.getInstance("SHA1withRSA");
		sig.initSign(key);
		sig.update(message);
		byte[] signature = sig.sign();
		
		byte[] signedMessage = ArrayUtils.concat(ArrayUtils.intToByteArray(signature.length), signature);
		return signedMessage;
	}
	
	public static byte[] encryptMessage(byte[] message, PublicKey key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		// dhmiourgoume ena summetriko kleidi gia na ginei pio grhgora h diadikasia
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		keygen.init(128, new SecureRandom());
		SecretKey symmetricKey = keygen.generateKey();
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		cipher.init(Cipher.WRAP_MODE, key);
		byte[] wrappedKey = cipher.wrap(symmetricKey);
		
//		System.out.println("Wrapped Key: " + ArrayUtils.bytes2String(wrappedKey));
		
		byte[] encryptedMessage = ArrayUtils.concat(ArrayUtils.intToByteArray(wrappedKey.length),wrappedKey);
		
		cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, symmetricKey);
		
		return ArrayUtils.concat(encryptedMessage, cipher.doFinal(message));
	}
	
	public static byte[] decryptMessage(byte[] wrappedMessage, PrivateKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		byte[] wrappedKeyLengthArray = Arrays.copyOf(
				wrappedMessage, 4);

		int wrappedKeyLength = ArrayUtils.byteArrayToInt(wrappedKeyLengthArray);
		byte[] wrappedKey = Arrays.copyOfRange(wrappedMessage, 4,
				4 + wrappedKeyLength);
		
		byte[] encryptedMsg = Arrays.copyOfRange(wrappedMessage, 4+wrappedKeyLength, wrappedMessage.length);
		
		 Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		 cipher.init(Cipher.UNWRAP_MODE, key);
		 Key symmetricKey = cipher.unwrap(wrappedKey, "AES",
		 Cipher.SECRET_KEY);
		
		 cipher = Cipher.getInstance("AES");
		 cipher.init(Cipher.DECRYPT_MODE, symmetricKey);

		return cipher.doFinal(encryptedMsg);
	}
}
