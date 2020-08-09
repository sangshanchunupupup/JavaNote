package javacore.chapter3.innerclass;

/**
 * @author SangShanChun
 * @date 2020年8月9日
 * @brief 局部内部类
 */
public class InnerClassTest2 {
	public static void main(String[] args) {

	}
}
class OuterClass2 {
	public Apple getOuterClass2() {
		int num = 1;
		//类似于方法内部的局部变量，访问权限仅局限于该方法内
		class InnerClass2 extends Apple {
			public void method() {
				//Java8之前，局部内部类访问的局部变量必须是final修饰的
				System.out.println(num);
			}
		}
		return new InnerClass2();
	}

}
class Apple {

}