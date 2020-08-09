package javacore.chapter3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author SangShanChun
 * @date 2020年8月8日
 * @brief 反射
 */
public class ReflectTest {

	public static void main(String[] args) {
		try {
			// 获取class对象实例
			Class clz = Class.forName("chapter3.Apple");
			// 根据class对象获取constructor对象
			Constructor appleConstructor = clz.getConstructor();
			// 使用constructor对象的newInstance方法获取反射类对象
			Object appleObj = appleConstructor.newInstance();
			// 获取方法的method对象
			Method setPrice = clz.getMethod("setPrice", int.class);
			// 利用invoke方法调用方法
			setPrice.invoke(appleObj, 14);

			Method getPrice = clz.getMethod("getPrice");
			System.out.println(getPrice.invoke(appleObj));

			Class cla = Apple.class;
			// getDeclaredFields获得所有域，getFields获得公有域
			Field[] fields = cla.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field.getName());
			}
			// 获取Class的方法，Class.forName(), .class, getClass();
			Class conClass = Apple.class;
			Constructor constructor = conClass.getConstructor(int.class);
			Apple apple = (Apple) constructor.newInstance(22);
			System.out.println(apple.getPrice());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Apple {
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Apple(int price) {
		this.price = price;
	}

	public Apple() {

	}
	
}
