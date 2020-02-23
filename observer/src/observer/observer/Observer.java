package observer.observer;

/**
 * 옵저버 인터페이스
 * 
 * @author user
 */
public interface Observer {
	public void receive(String channel, String user, String contents);
}
