package dev.mateusneres.report.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mateusneres.report.entities.Address;
import dev.mateusneres.report.exceptions.AddressNotFoundException;
import dev.mateusneres.report.exceptions.ApiKeyUnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class MapQuestService {

    @Value("${MAPQUEST_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public MapQuestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Address getReverseGeoAddress(double latitude, double longitude) {
        String url = "http://www.mapquestapi.com/geocoding/v1/reverse?key={key}&location={latitude},{longitude}";
        String geocodeUrl = url.replace("{key}", apiKey)
                .replace("{latitude}", String.valueOf(latitude))
                .replace("{longitude}", String.valueOf(longitude));

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(geocodeUrl, String.class);
            String responseBody = responseEntity.getBody();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            JsonNode locationNode = root.path("results").get(0).path("locations").get(0);

            String pais = locationNode.path("adminArea1").asText();
            if (pais.isEmpty()) return null;

            return new Address(latitude, longitude, locationNode.path("street").asText(),
                    locationNode.path("adminArea6").asText(), locationNode.path("adminArea5").asText(),
                    locationNode.path("adminArea3").asText(), pais, locationNode.path("postalCode").asText());

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            HttpStatus statusCode = e.getStatusCode();

            if (statusCode == HttpStatus.FORBIDDEN) {
                throw new ApiKeyUnauthorizedException(3, "A api key do mapquest não foi autorizada!");
            }

            if (statusCode == HttpStatus.NOT_FOUND) {
                throw new AddressNotFoundException(2, "Endereço não encontrado para essa localidade.");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
