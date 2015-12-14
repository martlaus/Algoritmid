package mitm;

import java.security.Key;

public class CommunicationChannel {
	
	public static void sendKeyTo(Actor recipient, Key key) {
		Mitm.eve.interceptKey(recipient, key);
	}
	
	public static void sendMsgTo(Actor recipient, String msg) {
		Mitm.eve.interceptMessage(recipient, msg);
	}
	
	public static void sendInterceptedKeyTo(Actor recipient, Key key) {
		recipient.finalizeDH(key);
	}
	
	public static void sendInterceptedMsgTo(Actor recipient, String msg) {
		recipient.receiveMessage(msg);
	}

}
