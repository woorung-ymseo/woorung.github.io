package singleton;

/**
 * 연결 인스턴스 생성 Thread
 * 
 * @author user
 */
public class RunnableForThread {
	private static int rNum = 0;
	
	/**
	 * Runnable 인터페이스의 추상 메서드가 run 1개 뿐이기 때문에
	 * 함수형 인터페이스 적용 
	 * (실제로 run 추상메서드가 overriding 되는 구조)
	 * 
	 * (Runnable을 implements 받지 않고 사용)
	 */
	Runnable runTask = () -> {
		ConnectionPool pool = ConnectionPool.getInstance(this.getInstanceName());
		
		System.out.println("#### 커넥션 명 : " + pool.getName());
	};
	
	public void task() {
		
		for (int i = 0; i < 30; i++) {
			Thread thread = new Thread(this.runTask);
			
			thread.start();
		}
	}
	
	public String getInstanceName() {
		rNum++;
		
		return "Connect " + rNum +  "번째";
	}
}
