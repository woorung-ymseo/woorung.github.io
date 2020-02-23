package singleton;

/**
 * ���� ��ü
 * 
 * @author user
 */
public class ConnectionPool {
	
	private static ConnectionPool connectionPool;
	private String connNm;
	
	/**
	 * �����ڷ� �ν��Ͻ� ������ ���� ���� private ���� ����ȭ
	 */
	private ConnectionPool() {}
	
	/**
	 * �����ڷ� �ν��Ͻ� ������ ���� ���� private ���� ����ȭ
	 * 
	 * @param connNm
	 */
	private ConnectionPool(String connNm) {
		super();
		
		this.connNm = connNm;
	}
	
	/**
	 * �ν��Ͻ� ����
	 * 
	 * synchronized : �����Ǵ� �ν��Ͻ��� ����ȭ�� �����ֱ� ���� ���
	 * @param connNm
	 * @return
	 */
	public static synchronized  ConnectionPool getInstance(String connNm) {
		
		if (connectionPool == null) {
			connectionPool = new ConnectionPool(connNm);
		}
		
		return connectionPool;
	}
	
	/**
	 * �ν��Ͻ� �̸� ��������
	 * 
	 * @return
	 */
	public String getName() {
		return connNm;
	}
}
