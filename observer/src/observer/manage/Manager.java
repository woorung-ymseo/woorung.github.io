package observer.manage;

import java.util.ArrayList;

import observer.observer.Observer;

/**
 * 옵저버 관리자
 * 
 * @author user
 */
public class Manager implements Management {
	private ArrayList<Observer> lObservers;
	private String channel;
	private String user;
	private String contents;

	public Manager() {
		this.lObservers = new ArrayList<Observer>();
	}
	
	public Manager(String channel) {
		this.channel 	= channel;
		this.lObservers = new ArrayList<Observer>();
	}

	/**
	 * 옵저버 추가
	 */
	@Override
	public void add(Observer observer) {
		this.lObservers.add(observer);
	}

	/**
	 * 옵저버 제거
	 */
	@Override
	public void delete(Observer observer) {
		this.lObservers.remove(this.lObservers.indexOf(observer));
	}

	/**
	 * 옵저버들에게 내용 전달
	 */
	@Override
	public void notifyObserver() {
		
		for (Observer observer : this.lObservers) {
			observer.receive(this.channel, this.user, this.contents);
		}
	}
	

	@Override
	public void notifyObserver(String user, String contents) {
		this.user 		= user;
		this.contents 	= contents;
		
		for (Observer observer : this.lObservers) {
			observer.receive(this.channel, this.user, this.contents);
		}
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
