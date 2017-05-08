package io.codingbros.talk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.codingbros.talk.helper.TestDataGenerator;
import io.codingbros.talk.restresource.GameMetaInfoResource;
import io.codingbros.talk.types.meta.GameMetaData;

public class Test_3 {

	@Rule
	public WireMockRule wireMock = new WireMockRule(wireMockConfig()
			.dynamicHttpsPort()
			.keystorePath("src/test/resources/wiremock.jks")
			.keystorePassword("password"));

	private static final String BASE_URL = "https://localhost:";

    private ObjectMapper jsonMapper = new ObjectMapper();

    private GameMetaInfoResource restResourceUnderTest;

	@Before
    public void setUp() {
        String targetUrl = BASE_URL + wireMock.httpsPort();
        restResourceUnderTest = RestResourceAdapter.getRestResource(GameMetaInfoResource.class, targetUrl);
    }

	@Test
	public void testPositive() throws JsonProcessingException {
		//arrange
		stubFor(get(urlEqualTo("/game/info/about"))
				.willReturn(aResponse()
						.withStatus(HttpStatus.SC_OK)
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
						.withBody(jsonMapper.writeValueAsString(TestDataGenerator.generateTestMetaData()))));

		//act
        Response result = restResourceUnderTest.loadAboutInformation();
		GameMetaData gameMetaData = result.readEntity(GameMetaData.class);

		//assert
		assertThat(result.getStatus()).isEqualTo(HttpStatus.SC_OK);
		assertThat(gameMetaData).isNotNull();
		assertThat(gameMetaData.getCreditsMap()).isNotNull();
		assertThat(gameMetaData.getCreditsMap().getCredits()).isNotNull();
		assertThat(gameMetaData.getCreditsMap().getCredits().get("developers")).contains("Tobias Spindler", "Andre Kramer");
		assertThat(gameMetaData.getCreditsMap().getCredits().get("founders")).contains("Shigeru Miyamoto", "Minoru Arakawa", "Hiroshi Yamauchi");
		assertThat(gameMetaData.getPlatform()).isEqualTo("PC");
		assertThat(gameMetaData.getTitle()).isEqualTo("Super Codingbros.");
		assertThat(gameMetaData.getVersion()).isEqualTo("Chapter I");
	}

	@Test (expected = ProcessingException.class)
	public void testNegative() throws JsonProcessingException {
        //arrange
        stubFor(get(urlEqualTo("/game/info/about"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .withFault(Fault.EMPTY_RESPONSE)));

        //act
        restResourceUnderTest.loadAboutInformation();
	}

}
