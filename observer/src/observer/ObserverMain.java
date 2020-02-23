package observer;

import observer.channel.Streaming;
import observer.manage.Manager;

public class ObserverMain {

	public static void main(String[] args) {
		Manager manager = new Manager("SEOUL");
		
		Streaming streamYoutuebe	= new Streaming(manager, "��Ʃ��");
		Streaming streamTwitch 		= new Streaming(manager, "Ʈ��ġ");
		Streaming streamAfreeca 	= new Streaming(manager, "������ī");
		
		manager.setUser("vggm004");
		manager.setContents("����");
		
		manager.notifyObserver();
		manager.notifyObserver("admin", "����");
	}
}
