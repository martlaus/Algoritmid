package mitm;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import dh.DH;
import exceptions.AESCipherException;
import exceptions.DHKeyAgreementException;
import exceptions.DHKeyException;

public class Eve {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private String lastAliceMessage = "";
    private String lastBobMessage = "";

    private Key bobKey;
    private Key aliceKey;
    private SecretKey secretKeyBOB;
    private SecretKey secretKeyALICE;


    //
    boolean initialized = false;

    public Eve(BigInteger p, BigInteger g) throws DHKeyException {
        KeyPair kp = DH.generateKeyPair(p, g);
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void interceptKey(Actor recipient, Key key) {
        //TODO: Edit this function
        if (recipient instanceof Bob) {
            System.out.println("Eve: [Alice --[key]--> Bob] key: " + DH.exportKey(key));
            bobKey = key;
            try {
                secretKeyBOB = (SecretKey) DH.generateAESKey(privateKey, (PublicKey) key);
            } catch (DHKeyAgreementException e) {
                e.printStackTrace();
            }

            CommunicationChannel.sendInterceptedKeyTo(recipient, publicKey);
        } else if (recipient instanceof Alice) {
            System.out.println("Eve: [Bob --[key]--> Alice] key: " + DH.exportKey(key));
            aliceKey = key;

            try {
                secretKeyALICE = (SecretKey) DH.generateAESKey(privateKey, (PublicKey) key);
            } catch (DHKeyAgreementException e) {
                e.printStackTrace();
            }

            CommunicationChannel.sendInterceptedKeyTo(recipient, publicKey);

        }
    }

    public void interceptMessage(Actor recipient, String msg) {
        //TODO: Edit this function
        if (recipient instanceof Bob) {
            System.out.println("Eve: [Alice --[msg]--> Bob] msg: " + msg);
            String decrypted = null;
            String encrypted = null;
            try {
                decrypted = DH.decryptAES(secretKeyBOB, msg);
                encrypted = DH.encryptAES(secretKeyBOB, decrypted);
            } catch (AESCipherException e) {
                e.printStackTrace();
            }

            CommunicationChannel.sendInterceptedMsgTo(recipient, encrypted);
        } else if (recipient instanceof Alice) {
            System.out.println("Eve: [Alice --[msg]--> Bob] msg: " + msg);
            String decrypted = null;
            String encrypted = null;
            try {
                decrypted = DH.decryptAES(secretKeyALICE, msg);
                encrypted = DH.encryptAES(secretKeyALICE, decrypted);
            } catch (AESCipherException e) {
                e.printStackTrace();
            }

            CommunicationChannel.sendInterceptedMsgTo(recipient, encrypted);
        }
    }

    public SecretKey getAliceSecret() {
        return secretKeyALICE;
    }

    public SecretKey getBobSecret() {
        return secretKeyBOB;
    }

    public String getLastAliceMessage() {
        return lastAliceMessage;
    }

    public String getLastBobMessage() {
        return lastBobMessage;
    }
}
