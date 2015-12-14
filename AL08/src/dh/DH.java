package dh;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import exceptions.AESCipherException;
import exceptions.DHKeyAgreementException;
import exceptions.DHKeyException;
import exceptions.DHKeyImportException;
import exceptions.DHParameterSpecException;

public class DH {

	public static KeyPair generateKeyPair(BigInteger p, BigInteger g) throws DHKeyException {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("DiffieHellman");
			kpg.initialize(new DHParameterSpec(p,g),new SecureRandom());
			return kpg.generateKeyPair();
		} catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
			throw new DHKeyException();
		}
	}
	
	public static DHParameterSpec genParamSpec(int keylength) throws DHParameterSpecException {
		try {
			AlgorithmParameterGenerator pGen = AlgorithmParameterGenerator.getInstance("DH");
			pGen.init(keylength);
			AlgorithmParameters params = pGen.generateParameters();
			return (DHParameterSpec) params.getParameterSpec(
				DHParameterSpec.class);
		} catch (NoSuchAlgorithmException | InvalidParameterSpecException e) {
			throw new DHParameterSpecException();
		}
		
	}
	
	public static BigInteger getPrime(Key key) {
		DHKey k = (DHKey) key;
		return k.getParams().getP();
	}
	
	public static BigInteger getGenerator(Key key) {
		DHKey k = (DHKey) key;
		return k.getParams().getG();
	}
	
	public static String exportKey(Key k) {
		return Base64.getEncoder().encodeToString(k.getEncoded());
	}
	
	public static PublicKey importPublicKey(String s) throws DHKeyImportException {
		try {
			byte[] publicKeyBytes = Base64.getDecoder().decode(s);
			KeyFactory kf = KeyFactory.getInstance("DH");
			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
			return kf.generatePublic(publicKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new DHKeyImportException();
		}
	}
	
	public static PrivateKey importPrivateKey(String s) throws DHKeyImportException {
		try {
			byte[] privateKeyBytes = Base64.getDecoder().decode(s);
			KeyFactory kf = KeyFactory.getInstance("DH");
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			return kf.generatePrivate(privateKeySpec);
		} catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new DHKeyImportException();
		}
	}
	
	public static SecretKey importAESKey(String s) {
		byte[] secretKeyBytes = Base64.getDecoder().decode(s);
		return new SecretKeySpec(secretKeyBytes,0,secretKeyBytes.length,"AES");
	}
	
	public static Key generateAESKey(PrivateKey privateKey, PublicKey publicKey) throws DHKeyAgreementException {
		try {
			KeyAgreement ka = KeyAgreement.getInstance("DH");
			ka.init(privateKey);
			ka.doPhase(publicKey, true);
			return ka.generateSecret("AES");
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new DHKeyAgreementException();
		}
	}
	
	public static String encryptAES(Key key, String plainText) throws AESCipherException {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cipherText = cipher.doFinal(plainText.getBytes(Charset.forName("UTF-8")));
			return Base64.getEncoder().encodeToString(cipherText);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new AESCipherException();
		}
	}
	
	public static String decryptAES(Key key, String cipherText) throws AESCipherException {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key, cipher.getParameters());
			return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new AESCipherException();
		}
	}
}
