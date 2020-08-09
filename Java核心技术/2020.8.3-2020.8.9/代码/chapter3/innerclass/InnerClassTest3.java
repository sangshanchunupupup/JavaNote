package javacore.chapter3.innerclass;

/**
 * @author SangShanChun
 * @date 2020年8月9日
 * @brief 匿名内部类
 */
public class InnerClassTest3 {
	public static void main(String[] args) {
		Test2 test2 = new Test2(new TestInterface() {
			@Override
			public void method() {
				System.out.println("method");
			}
		});
		test2.method();

		// Lambda写法
		Test2 test21 = new Test2(() -> {
			System.out.println("method");
		});
		test21.method();
	}
}

interface TestInterface {
	void method();
}

class Test2 {
	private TestInterface testInterface;
	public Test2(TestInterface testInterface) {
		this.testInterface = testInterface;
	}
	public void method() {
		testInterface.method();
	}
}
