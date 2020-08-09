package javacore.chapter3.innerclass;

/**
 * @author SangShanChun
 * @date 2020年8月9日
 * @brief 成员内部类
 */
public class InnerClassTest1 {

	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();
		//创建内部类对象方式一
		OuterClass.InnerClass innerClass = outerClass.new InnerClass();
		//创建内部类对象方式二
		OuterClass.InnerClass innerClass1 = outerClass.getInnerClass();
	}

}
class OuterClass {
	int num = 0;
	private String str = "ABC";
	public static String line = "EDF";
	private InnerClass innerClass = null;

	public InnerClass getInnerClass() {
		if(null == innerClass) {
			innerClass = new InnerClass();
		}
		return innerClass;
	}

	/**
	 * 作为外部类的一个属性，可以被private、protected、public、default修饰
	 */
	class InnerClass {
		int num = 1;
		public void method() {
			//成员内部类可以访问外部类的所有成员属性和成员方法，包括私有和静态成员
			System.out.println(str);
			System.out.println(line);
			System.out.println(num);
			//出现同名的成员变量或方法时，会发生隐藏现象，默认访问内部类的方法和变量，访问外部：外部类.this.成员变量/方法
			System.out.println(OuterClass.this.num);
		}
	}
}
