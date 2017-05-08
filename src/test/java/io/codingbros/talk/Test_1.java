package io.codingbros.talk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.codingbros.talk.restresource.GameMetaInfoResource;
import io.codingbros.talk.types.Difficulty;

public class Test_1 {

	@Rule
	public WireMockRule wireMock = new WireMockRule(wireMockConfig().dynamicPort());

	private static final String BASE_URL = "http://localhost:";

    private GameMetaInfoResource restResourceUnderTest;

    @Before
    public void setUp() {
        String targetUrl = BASE_URL + wireMock.port();
        restResourceUnderTest = RestResourceAdapter.getRestResource(GameMetaInfoResource.class, targetUrl);
    }

	@Test
	public void testPositive() {
		//arrange
		stubFor(get(urlEqualTo("/game/info/difficulty"))
				.willReturn(aResponse()
						.withStatus(HttpStatus.SC_OK)
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
						.withBody(Difficulty.OVER9000.name())));

		//act
		Response result = restResourceUnderTest.loadCurrentDifficulty();

		//assert
		assertThat(result.getStatus()).isEqualTo(HttpStatus.SC_ACCEPTED);
		assertThat(result.readEntity(String.class)).contains("OVER9000");
	}

	@Test
	public void testNegative() {
		//arrange
		stubFor(get(urlEqualTo("/game/info/difficulty"))
				.willReturn(aResponse()
						.withStatus(HttpStatus.SC_NOT_FOUND)
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
						.withBody("404 - Not Found !!")));

		//act
		Response result = restResourceUnderTest.loadCurrentDifficulty();

		//assert
		assertThat(result.getStatus()).isEqualTo(HttpStatus.SC_NOT_FOUND);
		assertThat(result.readEntity(String.class)).contains("404 - Not Found !!");
	}


}
