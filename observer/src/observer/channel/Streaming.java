package observer.channel;

import observer.manage.Manager;
import observer.observer.Observer;

public class Streaming implements Observer {
	private Manager manager;
	private String streaming = "";
	private String chat;
	
	public Streaming(Manager manager, String streaming) {
		this.manager 	= manager;
		this.streaming 	= streaming;
		
		manager.add(this);
	}
	
	@Override
	public void receive(String channel, String user, String contents) {
		this.chat = "[" + this.streaming + "]" + " [" + channel + "]" + " [" + user + "] : " + contents;
		
		System.out.println(this.chat);
	}
}
