package io.codingbros.talk.types;

public enum Princess {

	PEACH("Peach", true),
	DAISY("Daisy", false);

	private String name;

	private Boolean inLoveWithMario;


	Princess(String name, Boolean inLoveWithMario) {
		this.name = name;
		this.inLoveWithMario = inLoveWithMario;
	}

	public String getName() {
		return name;
	}

	public Boolean getInLoveWithMario() {
		return inLoveWithMario;
	}
}
