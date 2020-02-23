package singleton;

/**
 * 연결 객체
 * 
 * @author user
 */
public class ConnectionPool {
	
	private static ConnectionPool connectionPool;
	private String connNm;
	
	/**
	 * 생성자로 인스턴스 생성을 막기 위해 private 으로 은닉화
	 */
	private ConnectionPool() {}
	
	/**
	 * 생성자로 인스턴스 생성을 막기 위해 private 으로 은닉화
	 * 
	 * @param connNm
	 */
	private ConnectionPool(String connNm) {
		super();
		
		this.connNm = connNm;
	}
	
	/**
	 * 인스턴스 생성
	 * 
	 * synchronized : 생성되는 인스턴스의 동기화를 맞춰주기 위해 사용
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
	 * 인스턴스 이름 가져오기
	 * 
	 * @return
	 */
	public String getName() {
		return connNm;
	}
}
