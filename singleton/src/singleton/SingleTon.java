package singleton;

public class SingleTon {

	public static void main(String[] args) {
		
		// �ν��Ͻ� Thread�� �ټ� ���� => synchronized�� ����Ͽ� ����ȭ ����
		RunnableForThread rThread = new RunnableForThread();
		
		// ������ ����
		rThread.task();
		
		// �ν��Ͻ� ���� => �̹� �����忡�� �ν��Ͻ��� �����Ͽ��⿡ �̱��� ��Ģ�� ���� ������ �ν��Ͻ� return
		ConnectionPool pool1 = ConnectionPool.getInstance("����1");
		ConnectionPool pool2 = ConnectionPool.getInstance("����2");
		
		System.out.println("### [pool 1 Name] : " + pool1.getName() + " | [HashCode] : " + pool1.hashCode());
		System.out.println("### [pool 2 Name] : " + pool2.getName() + " | [HashCode] : " + pool2.hashCode());
		
		pool1 = ConnectionPool.getInstance("����3");

		System.out.println("### [pool 3 Name] : " + pool1.getName() + " | [HashCode] : " + pool1.hashCode());
	}
}
