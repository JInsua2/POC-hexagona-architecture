package com.insua.hexagonalspringbootdemo.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PokemonRepositoryImpl implements com.insua.hexagonalspringbootdemo.domain.repository.PokemonRepository {


    @SneakyThrows
    @Override
    public List<String> getPokemons() {
        HttpResponse<String> response = executeRequest();

        if (response.statusCode() == 200) {
            String json = response.body();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json);

                return StreamSupport.stream(jsonNode.get("results").spliterator(), false)
                    .map(result -> result.get("name").asText())
                    .toList();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    private static HttpResponse<String> executeRequest() throws NoSuchAlgorithmException, KeyManagementException, IOException, InterruptedException {
        TrustManager[] trustAllCertificates = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        // Configura SSL context con el trust manager personalizado
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());

        // Configura HttpClient para utilizar el SSLContext personalizado
        HttpClient httpClient = HttpClient.newBuilder()
            .sslContext(sslContext)
            .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://pokeapi.co/api/v2/pokemon"))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}
