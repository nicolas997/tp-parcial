package tp.external.google;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.utils.URLParamEncoder;

public class GoogleApiClient {

    private final String API_URL = "https://maps.googleapis.com/maps/api";
    private final String API_KEY = System.getenv("GOOGLE_MAPS_API_KEY");
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final GoogleApiClient instance = new GoogleApiClient();

    private GoogleApiClient() {}

    public static GoogleApiClient getInstance() {
        return instance;
    }

    public GoogleGeocodeResult geocodeAddress(String address) {
        String url = API_URL + "/geocode/json?address=" + URLParamEncoder.encode(address) + "&key=" + API_KEY;

        try {
            HttpResponse<String> response = Unirest.get(url).asString();
            String bodyAsString = response.getBody();

            JsonNode node = objectMapper.readTree(bodyAsString);

            ArrayNode results = (ArrayNode) node.get("results");

            if (results.size() < 1) {
                return null;
            }

            JsonNode firstResult = results.get(0);
            JsonNode location    = firstResult.get("geometry").get("location");

            return new GoogleGeocodeResult(
                    firstResult.get("formatted_address").asText(),
                    location.get("lat").floatValue(),
                    location.get("lng").floatValue()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
