package singleton;

public class SingleTon {

	public static void main(String[] args) {
		
		// 인스턴스 Thread로 다수 생성 => synchronized를 사용하여 동기화 적용
		RunnableForThread rThread = new RunnableForThread();
		
		// 스레드 실행
		rThread.task();
		
		// 인스턴스 생성 => 이미 스레드에서 인스턴스를 생성하였기에 싱글톤 규칙에 따라 동일한 인스턴스 return
		ConnectionPool pool1 = ConnectionPool.getInstance("접속1");
		ConnectionPool pool2 = ConnectionPool.getInstance("접속2");
		
		System.out.println("### [pool 1 Name] : " + pool1.getName() + " | [HashCode] : " + pool1.hashCode());
		System.out.println("### [pool 2 Name] : " + pool2.getName() + " | [HashCode] : " + pool2.hashCode());
		
		pool1 = ConnectionPool.getInstance("접속3");

		System.out.println("### [pool 3 Name] : " + pool1.getName() + " | [HashCode] : " + pool1.hashCode());
	}
}
