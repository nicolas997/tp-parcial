package tp.model.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import tp.external.google.GoogleApiClient;
import tp.external.google.GoogleGeocodeResult;
import tp.model.utils.Country;
import tp.model.utils.WithId;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BranchOffice implements WithId {

	String id;

	Country country;
	String phoneNumber;

	String displayableAddress;
	Float addressLatitude;
	Float addressLongitude;

	public BranchOffice (Country country, String phoneNumber, String address) {
		this(UUID.randomUUID().toString(), country, phoneNumber, null, null, null);

		GoogleGeocodeResult geocodeResult = GoogleApiClient.getInstance().geocodeAddress(address);
		if (geocodeResult == null) {
			// no se pudo encontrar la direccion
			throw new RuntimeException("address " + address + " was not found, try again");
		} else {
			this.displayableAddress = geocodeResult.getFormattedAddress();
			this.addressLatitude    = geocodeResult.getLatitude();
			this.addressLongitude   = geocodeResult.getLongitude();
		}
	}
}
