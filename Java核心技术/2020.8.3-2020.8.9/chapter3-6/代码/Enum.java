package javacore.chapter3;

import java.util.Scanner;

/**
 * @author SangShanChun
 * @date 2020年8月8日
 * @brief 枚举类
 */
public class Enum {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next().toUpperCase();
		Size size = Size.valueOf(Size.class, input);

		System.out.println("size = " + size);
		System.out.println("abbrivation = " + size.getAbbrivation());

		String input2 = scanner.next().toUpperCase();
		Money money = Money.valueOf(Money.class, input2);
		System.out.println("money = " + money);
		System.out.println("number = " + money.getNumber());

	}

}

enum Size {
	SMALL("S"), MEDIUM("M"), LARGE("L");
	private String abbrivation;

	public String getAbbrivation() {
		return abbrivation;
	}

	Size(String abbrivation) {
		this.abbrivation = abbrivation;
	}
}

enum Money {
	TEN(10), TWENTY(22), ZERO(0);
	private Integer number;

	public Integer getNumber() {
		return number;
	}

	Money(Integer number) {
		this.number = number;
	}
}
