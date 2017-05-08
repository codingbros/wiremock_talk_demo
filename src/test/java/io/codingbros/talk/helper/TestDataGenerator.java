package io.codingbros.talk.helper;

import io.codingbros.talk.types.meta.Credits;
import io.codingbros.talk.types.meta.GameMetaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataGenerator {

	public static GameMetaData generateTestMetaData() {
		GameMetaData gameMetaData = new GameMetaData();

		gameMetaData.setCreditsMap(generateTestCredits());
		gameMetaData.setPlatform("PC");
		gameMetaData.setTitle("Super Codingbros.");
		gameMetaData.setVersion("Chapter I");

		return gameMetaData;
	}

	public static Credits generateTestCredits() {
		Credits credits = new Credits();
		Map<String, List<String>> map = new HashMap<>();

		map.put("developers", generateDevelopers());
		map.put("founders", generateFounders());

		credits.setCredits(map);

		return credits;
	}

	private static List<String> generateFounders() {
		List<String> founders = new ArrayList<>();

		founders.add("Shigeru Miyamoto");
		founders.add("Minoru Arakawa");
		founders.add("Hiroshi Yamauchi");

		return founders;
	}

	private static List<String> generateDevelopers() {
		List<String> developers = new ArrayList<>();

		developers.add("Tobias Spindler");
		developers.add("Andre Kramer");

		return developers;
	}

}
