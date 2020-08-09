package javacore.chapter3;

/**
 * @author SangShanChun
 * @date 2020年8月8日
 * @brief 回调
 */
public class CallBackTest {

	public static void main(String[] args) {
		new Teacher(new Merry()).askQuestion();
	}

}

interface CallBack {
	void tellAnswer(Integer num);
}

interface Stu {
	void resolveQuestion(CallBack callBack);
}

class Teacher implements CallBack{
	private Stu stu;
	public Teacher(Stu stu) {
		this.stu = stu;
	}

	@Override
	public void tellAnswer(Integer num) {
		System.out.println("答案是：" + num);
	}
	
	public void askQuestion() {
		//传入自身实例对象
		stu.resolveQuestion(this);
	}

}

class Merry implements Stu {

	@Override
	public void resolveQuestion(CallBack callBack) {
		callBack.tellAnswer(10);
	}

}
