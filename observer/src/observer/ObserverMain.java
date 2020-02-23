package observer;

import observer.channel.Streaming;
import observer.manage.Manager;

public class ObserverMain {

	public static void main(String[] args) {
		Manager manager = new Manager("SEOUL");
		
		Streaming streamYoutuebe	= new Streaming(manager, "유튜브");
		Streaming streamTwitch 		= new Streaming(manager, "트위치");
		Streaming streamAfreeca 	= new Streaming(manager, "아프리카");
		
		manager.setUser("vggm004");
		manager.setContents("ㅎㅇ");
		
		manager.notifyObserver();
		manager.notifyObserver("admin", "공지");
	}
}
