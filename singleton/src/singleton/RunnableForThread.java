package singleton;

public class RunnableForThread {
	private static int rNum = 0;
	
	public void task() {
		
		/**
		 * Runnable �������̽��� �߻� �޼��尡 run 1�� ���̱� ������
		 * �Լ��� �������̽� ���� 
		 * (������ run �߻�޼��尡 overriding �Ǵ� ����)
		 */
		Runnable runTask = () -> {
			ConnectionPool pool = ConnectionPool.getInstance(getInstanceName());
			
			System.out.println("#### Ŀ�ؼ� �� : " + pool.getName());
		};
		
		for (int i = 0; i < 30; i++) {
			Thread thread = new Thread(runTask);
			
			thread.start();
		}
	}
	
	public String getInstanceName() {
		rNum++;
		
		return "Connect " + rNum +  "��°";
	}
}
