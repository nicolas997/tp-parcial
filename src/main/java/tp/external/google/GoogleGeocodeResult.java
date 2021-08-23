package tp.external.google;

public class GoogleGeocodeResult {

    String formattedAddress;
    float latitude;
    float longitude;

    public GoogleGeocodeResult(String formattedAddress, float latitude, float longitude) {
        this.formattedAddress = formattedAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "GoogleGeocodeResult("+formattedAddress+", "+latitude+", "+longitude+")";
    }

}
