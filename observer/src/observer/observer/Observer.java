package observer.observer;

/**
 * ������ �������̽�
 * 
 * @author user
 */
public interface Observer {
	public void receive(String channel, String user, String contents);
}
