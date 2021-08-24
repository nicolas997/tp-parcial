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

	@Test
	public void Client1LocationFoundRight(){
		Float location[] = {
				-34.768958F,
				-58.426646F
		};
		Assert.assertArrayEquals(location, new Float[]{this.clients.get(1).getAddressLatitude(), this.clients.get(1).getAddressLongitude()});
	}

	@Test
	public void Client2LocationFoundRight(){
		Float location[] = {
				-34.706688F,
				-58.401557F
		};
		Assert.assertArrayEquals(location, new Float[]{this.clients.get(2).getAddressLatitude(), this.clients.get(2).getAddressLongitude()});
	}

	@Test
	public void Client3LocationFoundRight(){
		Float location[] = {
				-34.57307F,
				-58.439379F
		};
		Assert.assertArrayEquals(location, new Float[]{this.clients.get(3).getAddressLatitude(), this.clients.get(3).getAddressLongitude()});
	}

}
