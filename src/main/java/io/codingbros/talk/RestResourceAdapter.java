package io.codingbros.talk;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class RestResourceAdapter {

	private static final Integer MAX_CONNECTIONS = 20;

	public static void main(String[] args) {
		//Just for testing purposes...

		/*
		 * Generate Keystore by following command:
		 *
		 * keytool -genkey -alias wiremock -keyalg RSA -keysize 1024 -validity 365 -keypass password -keystore wiremock.jks -storepass password
		 *
		 * ATTENTION! CN (name- and surname must be localhost)
		 */
	}

	static <T> T getRestResource(Class<T> resourceClass, String url){

		ResteasyClient client = new ResteasyClientBuilder()
				.connectionPoolSize(MAX_CONNECTIONS)
				.keyStore(loadTestKeyStoreFromResources(), "password")
				.build();

		ResteasyWebTarget target = client.target(url);

		return target.proxyBuilder(resourceClass).build();
	}

	private static KeyStore loadTestKeyStoreFromResources() {
		KeyStore keystore = null;
		try {
			keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			InputStream keystoreInput = RestResourceAdapter.class.getResourceAsStream("/wiremock.jks");

			keystore.load(keystoreInput, "password".toCharArray());
		} catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return keystore;
	}

}
