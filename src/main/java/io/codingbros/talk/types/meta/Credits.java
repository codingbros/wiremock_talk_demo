package io.codingbros.talk.types.meta;

import java.util.List;
import java.util.Map;

public class Credits {

	private Map<String, List<String>> credits;

	public Credits() {
	}

	public Map<String, List<String>> getCredits() {
		return credits;
	}

	public void setCredits(Map<String, List<String>> credits) {
		this.credits = credits;
	}
}
