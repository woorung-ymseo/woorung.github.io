package observer.manage;

import observer.observer.Observer;

/**
 * 可历滚 包府 牢磐其捞胶
 * 
 * @author user
 *
 */
public interface Management {
	public void add(Observer observer);
	public void delete(Observer observer);
	public void notifyObserver();
	public void notifyObserver(String user, String contents);
}
