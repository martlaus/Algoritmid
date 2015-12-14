package mitm;
import java.security.Key;

import javax.crypto.SecretKey;

public interface Actor {
	
	public void initializeDH();
	public void finalizeDH(Key key);
	public void receiveMessage(String msg);
	public void sendMessage(String msg);
	public SecretKey getSecret();
}
