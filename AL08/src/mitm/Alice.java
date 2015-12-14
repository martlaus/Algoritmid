package mitm;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import dh.DH;
import exceptions.AESCipherException;
import exceptions.DHKeyAgreementException;
import exceptions.DHKeyImportException;

public class Alice implements Actor {
	
	PublicKey publicKey; 
	PrivateKey privateKey;
	SecretKey secretKey;
	
	boolean first = false;
	
	public Alice() {
		try {
			publicKey = DH.importPublicKey("MIIDKjCCAhwGCSqGSIb3DQEDATCCAg0CggEBAKzXP+U28M2Xf7YrEVE9JnARzBQHpr+/SO4DX/BkuBZ7II+UJpmkk3WZiAMvYo2DKUjjjDufM0But2e6u24gkRm9MLqVkfoaWpV7/lJQEp98HXOLFEf78HU5aWqQNNETT60vNW4Nj/qolNKls3mqmKzJ33KapbeF9NwMSkfxJ5+GhCgdOY9iRhAR9oiDgCPooGs127zuIh9owUW6Xr34IfaevoRM7Orf4DFd5qGZynmLn8YlwZQ9B+e1beahuLDKV81hBqqOUVBjBLgzzQYWkxDdVqBa6+K4ElJZAptNLr7ORFPBbk+OATF3LpLzhSegbHYiXdNUcpGDIRFolVdcP6sCggEAJKBvZ3VHyoABdnNaZWgsabVMGriRjHRtM+NMnPHRx4V1sARlhRepT0VuMxLOJ99bbMMHofP3JMQtRy235U9y+5v6M19aqhqedsFsXtJ8eG3m8vZIxCaUWfQQCzc2Km7GLPCaO0EXqbtf0HvYvOw9jr8gAUJwK7+pGYHbnS2X9rfjFXxuhZjlyslcGTyvECA1aoSOEdPt4CsH3Yq5LsIDaK6arLad9JTgwZ27MGexKaaptn1uuEvyKr0FlOy1DqefjBapgfRC21XPElxcP3GSzOvOT9AR2C9xR27XewI01kXCZomAcIkC2r0P+UWwEooL93ctjbIUykx5dqyhx1eUxwICBAADggEGAAKCAQEApWLHvapBITPr9F23aFDZ2OURzM7Fq+z5h+YjV1hYRI1niXQyDDr3rpal8uln24wv0MzkAFyf3ex9+5oiLZlDQjH6Mv3fBKY3TvsfRhYYyPFpkquf6nDJl4xvQ5F4zWUbPnxIv8UrZEQ8Ph9EW14tJtTPWvRdYHS/+AmSQGIzKDzIgXNFgIorzi2js0rTAdud7PKvLU3RWjawg8XNLF34xgzI5pSypQLDLbE9u/lyGg/qFaPLjdmD54SiMJoOvRZCXF8Zijuz2FPLfzqOB6lRwD4ES/CyedYxP7yvg2OFW7EMmbl+xUBiAJDh130WE4XCZcqhKxWX23B4OLapOGLhGQ==");
			privateKey = DH.importPrivateKey("MIICqgIBADCCAhwGCSqGSIb3DQEDATCCAg0CggEBAKzXP+U28M2Xf7YrEVE9JnARzBQHpr+/SO4DX/BkuBZ7II+UJpmkk3WZiAMvYo2DKUjjjDufM0But2e6u24gkRm9MLqVkfoaWpV7/lJQEp98HXOLFEf78HU5aWqQNNETT60vNW4Nj/qolNKls3mqmKzJ33KapbeF9NwMSkfxJ5+GhCgdOY9iRhAR9oiDgCPooGs127zuIh9owUW6Xr34IfaevoRM7Orf4DFd5qGZynmLn8YlwZQ9B+e1beahuLDKV81hBqqOUVBjBLgzzQYWkxDdVqBa6+K4ElJZAptNLr7ORFPBbk+OATF3LpLzhSegbHYiXdNUcpGDIRFolVdcP6sCggEAJKBvZ3VHyoABdnNaZWgsabVMGriRjHRtM+NMnPHRx4V1sARlhRepT0VuMxLOJ99bbMMHofP3JMQtRy235U9y+5v6M19aqhqedsFsXtJ8eG3m8vZIxCaUWfQQCzc2Km7GLPCaO0EXqbtf0HvYvOw9jr8gAUJwK7+pGYHbnS2X9rfjFXxuhZjlyslcGTyvECA1aoSOEdPt4CsH3Yq5LsIDaK6arLad9JTgwZ27MGexKaaptn1uuEvyKr0FlOy1DqefjBapgfRC21XPElxcP3GSzOvOT9AR2C9xR27XewI01kXCZomAcIkC2r0P+UWwEooL93ctjbIUykx5dqyhx1eUxwICBAAEgYQCgYEAqAOHW7CiAWRhgmvyb6RvOhRbkGHSItN9LgDPgS4xEDQ1E1NaSmjlzaYF04FieMSS2r8Yd+T4sjxRd2nRKTWEeSg8mlzKqIrZLWuYMToQN1zH0JITe0Hggnvd5AVGoAvGOy2cHWsEfCB7ookYiTGLQYucDsO49Vwb58yJKjLyo34=");
		} catch (DHKeyImportException e) {
			System.err.println("Alice failed to import her keys");
			System.exit(0);
		}
	}
	
	@Override
	public void initializeDH() {
		first = true;
		System.out.println("[INFO]: Alice initiates key agreement with Bob");
		CommunicationChannel.sendKeyTo(Mitm.bob, publicKey);
	}

	@Override
	public void finalizeDH(Key key) {
		try {
			secretKey = (SecretKey) DH.generateAESKey(privateKey, (PublicKey) key);
			System.out.println("[INFO]: Alice created a secret key: " + DH.exportKey(secretKey));
			if(!first) {
				initializeDH();
			}
		} catch (DHKeyAgreementException e) {
			System.err.println("Alice failed to agree on a shared key with Bob");
			System.exit(0);
		}
	}

	@Override
	public void receiveMessage(String msg) {
		try {
			System.out.println("[INFO]: Alice received a message from Bob: " + DH.decryptAES(secretKey, msg));
		} catch (AESCipherException e) {
			System.err.println("Alice failed to receive a message from Bob");
			System.exit(0);
		}
	}

	@Override
	public void sendMessage(String msg) {
		try {
			System.out.println("[INFO]: Alice sent a message to Bob: " + msg);
			CommunicationChannel.sendMsgTo(Mitm.bob, DH.encryptAES(secretKey, msg));
		} catch (AESCipherException e) {
			System.err.println("Alice failed to send a message to Bob");
			System.exit(0);
		}
	}

	@Override
	public SecretKey getSecret() {
		return secretKey;
	}

}
