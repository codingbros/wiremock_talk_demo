package io.codingbros.talk.types;

public class MainCharacter {

	private String name;

	private Castle castle;

	private Princess princess;

	public MainCharacter() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Castle getCastle() {
		return castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

	public Princess getPrincess() {
		return princess;
	}

	public void setPrincess(Princess princess) {
		this.princess = princess;
	}

	Boolean isPissedOff() {
		return princess == null;
	}
}
