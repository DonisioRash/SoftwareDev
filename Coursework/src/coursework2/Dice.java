package coursework2;

import java.util.Random;

public class Dice {
	private static final Random rand = new Random();
	private int value;

	public Dice() {
		value = rand.nextInt(6) + 1;
	}

	public int getValue() {
		return value;
	}
}