package javacore.chapter3;

/**
 * @author SangShanChun
 * @date 2020年8月9日
 * @brief 接口
 */
public class InterfaceTest {

	public static void main(String[] args) {
		Animal dog = new Dog();
		dog.bark();

		Student student = new Student(10);
		System.out.println(student.compareTo(new Student(5)));
	}
	
}
interface A {

}
interface B {

}
interface Animal extends A, B{
	/**
	 * 域属于public static final，变量必须初始化
	 */
	String name = "animal";

	/**
	 * 方法属于public
	 */
	void bark();


}

class Dog implements Animal {
	String name = "Dog";

	@Override
	public void bark() {
		System.out.println(Animal.name);
		System.out.println(name + ": 汪");
	}
}

class Student implements Comparable<Student> {
	private Integer age;

	public Student(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public int compareTo(Student o) {
		return this.age - o.getAge();
	}

}