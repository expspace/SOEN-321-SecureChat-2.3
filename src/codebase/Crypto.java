package codebase;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Crypto {
	
	/** DIFFIE-HELLMAN **/
	//asymmetric key cryptography methods for generating diffie-hellman public/private key pairs
	
	
	
	
	
	
	/** SIGNATURES **/
	//methods using SHA-256 with RSA to sign/verify the diffie-hellman public key
	
	public static byte[] getSignature(PrivateKey privateKey, byte[] data) {
		try {
			Signature sig = Signature.getInstance("SHA256withRSA"); //set algorithm
			sig.initSign(privateKey);  //set private key to sign
			sig.update(data); //set data to sign
			return sig.sign(); //return signature	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean verifySignature(PublicKey publicKey, byte[] signature, byte[] data) {
		try {
			Signature sig = Signature.getInstance("SHA256withRSA"); //set algorithm
			sig.initVerify(publicKey);  //set public key to verify
			sig.update(data); //set data to verify
			return sig.verify(signature); //verifies signature	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** CERTIFICATES **/
	//methods for accessing certificate private/public keys 

//	public static PublicKey loadCertificatePublicKey(String filepath) {
//		
//	}
//	
//	public static PrivateKey loadCertificatePrivateKey(String filepath) {
//		
//	}
	
	/** AES CIPHER **/
	//symmetric key cryptography methods for encrypting/decrypting exchanged messages
	
	public static byte[] getEncryptedMessage(SecretKey secretKey, byte[] message) {
		Cipher cipher;
		byte[] iv = new byte[16]; //TODO : Generate unique iv each time message is encrypted (prevents replay attacks?)
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
			return cipher.doFinal(message);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static byte[] getDecryptedMessage(SecretKey secretKey, byte[] message, byte[] iv) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
			return cipher.doFinal(message);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}	
}