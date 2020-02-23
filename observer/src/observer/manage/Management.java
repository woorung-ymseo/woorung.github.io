package observer.manage;

import observer.observer.Observer;

/**
 * ������ ���� �������̽�
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
