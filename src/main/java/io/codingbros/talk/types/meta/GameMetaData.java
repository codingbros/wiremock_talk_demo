package io.codingbros.talk.types.meta;

public class GameMetaData {

	private String title;

	private String version;

	private String platform;

	private Credits creditsMap;

	public GameMetaData() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Credits getCreditsMap() {
		return creditsMap;
	}

	public void setCreditsMap(Credits creditsMap) {
		this.creditsMap = creditsMap;
	}
}
