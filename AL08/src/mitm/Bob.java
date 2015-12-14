package mitm;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import dh.DH;
import exceptions.AESCipherException;
import exceptions.DHKeyAgreementException;
import exceptions.DHKeyImportException;

public class Bob implements Actor {
	
	PublicKey publicKey;
	PrivateKey privateKey;
	SecretKey secretKey;
	
	boolean first = false;

	public Bob() {
		try {
			publicKey = DH.importPublicKey("MIIDKTCCAhwGCSqGSIb3DQEDATCCAg0CggEBAKzXP+U28M2Xf7YrEVE9JnARzBQHpr+/SO4DX/BkuBZ7II+UJpmkk3WZiAMvYo2DKUjjjDufM0But2e6u24gkRm9MLqVkfoaWpV7/lJQEp98HXOLFEf78HU5aWqQNNETT60vNW4Nj/qolNKls3mqmKzJ33KapbeF9NwMSkfxJ5+GhCgdOY9iRhAR9oiDgCPooGs127zuIh9owUW6Xr34IfaevoRM7Orf4DFd5qGZynmLn8YlwZQ9B+e1beahuLDKV81hBqqOUVBjBLgzzQYWkxDdVqBa6+K4ElJZAptNLr7ORFPBbk+OATF3LpLzhSegbHYiXdNUcpGDIRFolVdcP6sCggEAJKBvZ3VHyoABdnNaZWgsabVMGriRjHRtM+NMnPHRx4V1sARlhRepT0VuMxLOJ99bbMMHofP3JMQtRy235U9y+5v6M19aqhqedsFsXtJ8eG3m8vZIxCaUWfQQCzc2Km7GLPCaO0EXqbtf0HvYvOw9jr8gAUJwK7+pGYHbnS2X9rfjFXxuhZjlyslcGTyvECA1aoSOEdPt4CsH3Yq5LsIDaK6arLad9JTgwZ27MGexKaaptn1uuEvyKr0FlOy1DqefjBapgfRC21XPElxcP3GSzOvOT9AR2C9xR27XewI01kXCZomAcIkC2r0P+UWwEooL93ctjbIUykx5dqyhx1eUxwICBAADggEFAAKCAQBTNs+7h9/5UAz+Gl7k/ldaguptnZqzsmVLNOSHBf1BDqQrs7KYKF9UtLeFS+yBTdiVCBSn1lrusjYFm4adCYtc5F69hQyorbOrYSAFpYuBdPyMRIp4ircWaHopcRXIgeNqblZWlz47T+kR5uQ6fDALbhRLOUXnaafRP2OrO8BhDeBcODiDdEqDn9fNQ473SONOc2nUd+a7ktxXdMqXjH8PBn5BtGx5FlikdCShUR5ncGh/WcIqeWhSgDlp4JY/UgRU95NnmwvquyH/esC+8qx2FmfrcejykzV9IrE+RD15TBj1X+v4LtGH78rQIT3uTIXcE5wg3sa+fbr289yMKbuH");
			privateKey = DH.importPrivateKey("MIICqgIBADCCAhwGCSqGSIb3DQEDATCCAg0CggEBAKzXP+U28M2Xf7YrEVE9JnARzBQHpr+/SO4DX/BkuBZ7II+UJpmkk3WZiAMvYo2DKUjjjDufM0But2e6u24gkRm9MLqVkfoaWpV7/lJQEp98HXOLFEf78HU5aWqQNNETT60vNW4Nj/qolNKls3mqmKzJ33KapbeF9NwMSkfxJ5+GhCgdOY9iRhAR9oiDgCPooGs127zuIh9owUW6Xr34IfaevoRM7Orf4DFd5qGZynmLn8YlwZQ9B+e1beahuLDKV81hBqqOUVBjBLgzzQYWkxDdVqBa6+K4ElJZAptNLr7ORFPBbk+OATF3LpLzhSegbHYiXdNUcpGDIRFolVdcP6sCggEAJKBvZ3VHyoABdnNaZWgsabVMGriRjHRtM+NMnPHRx4V1sARlhRepT0VuMxLOJ99bbMMHofP3JMQtRy235U9y+5v6M19aqhqedsFsXtJ8eG3m8vZIxCaUWfQQCzc2Km7GLPCaO0EXqbtf0HvYvOw9jr8gAUJwK7+pGYHbnS2X9rfjFXxuhZjlyslcGTyvECA1aoSOEdPt4CsH3Yq5LsIDaK6arLad9JTgwZ27MGexKaaptn1uuEvyKr0FlOy1DqefjBapgfRC21XPElxcP3GSzOvOT9AR2C9xR27XewI01kXCZomAcIkC2r0P+UWwEooL93ctjbIUykx5dqyhx1eUxwICBAAEgYQCgYEA7koOfJFnk8f2FVdUWc3pj507dAw4bW15CL4p8k6O/meC1tilBsdq7x+5Cp4z5WMtCYB6M8VlR2tpzr/yd7JxuSps5iiebzZxEA2smYxCFEGwH2Zim9bu+PNLsmLd3TbBEVFbdsNx5mXvi5924SM30wPu6M5BOG2hvNQfHD+MhNc=");
		} catch (DHKeyImportException e) {
			System.err.println("Bob failed to import his keys");
			System.exit(0);
		}
	}

	@Override
	public void initializeDH() {
		first = true;
		System.out.println("[INFO]: Bob initiates key agreement with Alice");
		CommunicationChannel.sendKeyTo(Mitm.alice, publicKey);
	}

	@Override
	public void finalizeDH(Key key) {
		try {
			secretKey = (SecretKey) DH.generateAESKey(privateKey, (PublicKey) key);
			System.out.println("[INFO]: Bob created a secret key: " + DH.exportKey(secretKey));
			if(!first) {
				initializeDH();
			}
		} catch (DHKeyAgreementException e) {
			System.err.println("Bob failed to agree on a shared key with Alice");
			System.exit(0);
		}
	}

	@Override
	public void receiveMessage(String msg) {
		try {
			System.out.println("[INFO]: Bob received a message from Alice: " + DH.decryptAES(secretKey, msg));
		} catch (AESCipherException e) {
			System.err.println("Bob failed to receive a message from Alice");
			System.exit(0);
		}
	}

	@Override
	public void sendMessage(String msg) {
		try {
			System.out.println("[INFO]: Bob sent a message to Alice: " + msg);
			CommunicationChannel.sendMsgTo(Mitm.alice, DH.encryptAES(secretKey, msg));
		} catch (AESCipherException e) {
			System.err.println("Bob failed to send a message to Alice");
			System.exit(0);
		}
	}

	@Override
	public SecretKey getSecret() {
		return secretKey;
	}
	
}
