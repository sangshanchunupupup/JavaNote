package javacore.chapter4.exception;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sangshanchun
 * @brief 异常
 * @date 2020/8/10 20:39
 */
public class ExceptionTest {
	/**
	 * 如果该日志不存在，创建日志记录器
	 */
	private static final Logger logger = Logger.getLogger("javacore.chapter4.ExceptionTest");
	private InputStream in;
	public static void main(String[] args) {
		// 调用抛出异常的方法需要重新抛出或者捕获处理
		try {
			method2();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loggerMethod();
		method3();
	}
	/**
	 * 一般情况下，finally块中的语句都会执行，当try块中有System.exit()时，finally块不执行
	 * finally块中的return语句会覆盖try中的return语句
	 */
	public void method() {
		// 方式一
		try {
			in = new FileInputStream("F:\\javafile");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// finally块释放资源或者关闭文件
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//方式二
		try {
			try {
				in = new FileInputStream("F:\\javafile");
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 抛出异常，调用者需要捕获或者重新抛出
	 */
	public static void method2() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.println(str);
	}
	public static void method3() {
		// 自定义异常
		try {
			throw new MyException("自定义异常");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 日志级别
	 */
	public static void loggerMethod() {
		logger.setLevel(Level.FINE);
		logger.config("nice");
		logger.fine("nice");
		logger.finest("nice");
		System.out.println("******");
		// 默认记录以下三种级别
		logger.info("nice");
		logger.warning("nice");
		logger.severe("nice");
	}
}
/**
 * 自定义异常
 */
class MyException extends Exception {
	public MyException() {
		super();
	}
	public MyException(String message) {
		super(message);
	}
	public MyException(String message, Throwable cause) {
		super(message, cause);
	}
	public MyException(Throwable cause) {
		super(cause);
	}
}
