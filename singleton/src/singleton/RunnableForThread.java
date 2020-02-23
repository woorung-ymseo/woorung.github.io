package singleton;

/**
 * ���� �ν��Ͻ� ���� Thread
 * 
 * @author user
 */
public class RunnableForThread {
	private static int rNum = 0;
	
	/**
	 * Runnable �������̽��� �߻� �޼��尡 run 1�� ���̱� ������
	 * �Լ��� �������̽� ���� 
	 * (������ run �߻�޼��尡 overriding �Ǵ� ����)
	 * 
	 * (Runnable�� implements ���� �ʰ� ���)
	 */
	Runnable runTask = () -> {
		ConnectionPool pool = ConnectionPool.getInstance(this.getInstanceName());
		
		System.out.println("#### Ŀ�ؼ� �� : " + pool.getName());
	};
	
	public void task() {
		
		for (int i = 0; i < 30; i++) {
			Thread thread = new Thread(this.runTask);
			
			thread.start();
		}
	}
	
	public String getInstanceName() {
		rNum++;
		
		return "Connect " + rNum +  "��°";
	}
}
