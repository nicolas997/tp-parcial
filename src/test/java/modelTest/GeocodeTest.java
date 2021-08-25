package modelTest;

import org.junit.Assert;
import org.junit.Test;

public class GeocodeTest extends BaseTest {

	@Test
	public void Client0LocationFoundRight(){
		Float location[] = {
				-34.588501F,
				-58.634356F
		};
		Assert.assertArrayEquals(location, new Float[]{this.clients.get(0).getAddressLatitude(), this.clients.get(0).getAddressLongitude()});
	}

}
