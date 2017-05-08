package io.codingbros.talk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.codingbros.talk.helper.TestDataGenerator;
import io.codingbros.talk.restresource.GameMetaInfoResource;
import io.codingbros.talk.types.meta.Credits;

public class Test_2 {

	@Rule
	public WireMockRule wireMock = new WireMockRule(wireMockConfig().dynamicPort());

	private static final String BASE_URL = "http://localhost:";

    private ObjectMapper jsonMapper = new ObjectMapper();

    private GameMetaInfoResource restResourceUnderTest;

    @Before
    public void setUp() {
        String targetUrl = BASE_URL + wireMock.port();
        restResourceUnderTest = RestResourceAdapter.getRestResource(GameMetaInfoResource.class, targetUrl);
    }

	@Test
	public void testPositive() throws JsonProcessingException {
		//arrange
		stubFor(get(urlEqualTo("/game/info/credits"))
				.willReturn(aResponse()
						.withStatus(HttpStatus.SC_OK)
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .withFixedDelay(100)
						.withBody(jsonMapper.writeValueAsString(TestDataGenerator.generateTestCredits()))));

		//act
		Response result = restResourceUnderTest.loadGameCredits();
		Credits credits = result.readEntity(Credits.class);

		//assert
		assertThat(result.getStatus()).isEqualTo(HttpStatus.SC_OK);
		assertThat(credits).isNotNull();
		assertThat(credits.getCredits()).isNotNull();
		assertThat(credits.getCredits().get("developers")).contains("Tobias Spindler", "Andre Kramer");
		assertThat(credits.getCredits().get("founders")).contains("Shigeru Miyamoto", "Minoru Arakawa", "Hiroshi Yamauchi");
	}

	@Test
	public void testNegative() {
		//arrange
		stubFor(get(urlEqualTo("/game/info/credits"))
				.willReturn(aResponse()
						.withStatus(HttpStatus.SC_NO_CONTENT)
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
						.withBody("401 - No Content")));

		//act
		Response result = RestResourceAdapter.getRestResource(GameMetaInfoResource.class, BASE_URL + wireMock.port()).loadGameCredits();
		Credits credits = result.readEntity(Credits.class);

		//assert
		assertThat(result.getStatus()).isEqualTo(HttpStatus.SC_NO_CONTENT);
		assertThat(credits).isNull();
	}
}
