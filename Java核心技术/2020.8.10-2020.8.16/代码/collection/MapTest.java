package javacore.chapter4.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sangshanchun
 * @brief map
 * @date 2020/8/15 10:17
 */
public class MapTest {
	public static void main(String[] args) {
		Student a = new Student("小明", 20);
		Student b = new Student("小明", 20);
		// true
		System.out.println(a.equals(b));
		Map<Student, String> map1 = new HashMap<>();
		map1.put(a, "a");
		map1.put(b, "b");
		// 注释掉hashCode()方法
		// 由于未重写hashcode方法，equals比较为ture的两个对象作为key可以put进
		// 同一个HashMap（key不可相同且最多有一个为null，value可相同可为null）
		// 输出a, b
		System.out.println(map1.get(a) +"\n" + map1.get(b));
		// 除去注释
		Map<Student, String> map2 = new HashMap<>();
		map2.put(a, "a");
		map2.put(b, "b");
		//输出b, b
		System.out.println(map2.get(a) +"\n" + map2.get(b));
	}
}

/**
 * 验证为什么重写equals方法后要重写hashcode的类
 */
class Student {
	private String name;
	private Integer age;

	public Student(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * 重写equals方法
	 */
	@Override
	public boolean equals(Object other) {
		if(other instanceof Student) {
			Student other1 = (Student) other;
			if(this.name.equals(other1.name) && this.age.equals(other1.age)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 重写hashcode方法
	 */
	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 17 * result + age.hashCode();
		return result;
	}
}