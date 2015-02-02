package ca.live.hk12.rando;

import ca.live.hk12.rando.randomizer.Randomizer;

public class RandoLauncher {
	
	private static final String fileName = "questions.txt";
	
	public static void main(String[] args) {
		new Randomizer(fileName);
	}
}
