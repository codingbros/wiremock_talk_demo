package io.codingbros.talk.types;

import java.util.ArrayList;
import java.util.List;

public class Castle {

	private Long id;

	private String name;

	private Difficulty difficulty = Difficulty.MEDIUM;

	private List<MainCharacter> characters = new ArrayList<>();

	public Castle(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public Boolean containsCharacter(MainCharacter character) {
		return characters.contains(character);
	}

	public Boolean removeCharacter(MainCharacter character) {
		return characters.remove(character);
	}

	public void addCharacter(MainCharacter character) {
		this.characters.add(character);
	}
}
