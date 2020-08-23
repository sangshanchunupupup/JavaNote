package concurrentprogram;

import java.sql.*;

/**
 * @author sangshanchun
 * @brief 线程封闭性
 * @date 2020/8/19 21:54
 */
public class ThreadCloseTest {
	private static String url = "";
	public static void main(String[] args) throws SQLException {

	}
	private static final ThreadLocal<Connection> connectionHolder
		= new ThreadLocal<Connection>() {
		/**
		 * 源码中initialValue()返回null，重写该方法后返回的值即为get()方法得到的值。
		 */
			@Override
			public Connection initialValue() {
				Connection connection = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection
							(url, "root", "passwd");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return connection;
			}
		};

	public static Connection getConnection() {
		return connectionHolder.get();
	}
}
