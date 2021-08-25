package modelTest;

import org.junit.Assert;
import org.junit.Test;

public class PriceTest extends BaseTest {

	@Test
	public void Purchase0PriceIs350(){
		Assert.assertEquals(350, this.purchases.get(0).getTotalPrice().intValue());
	}

	@Test
	public void Purchase1PriceIs(){
		Assert.assertEquals(750, this.purchases.get(1).getTotalPrice().intValue());
	}

	@Test
	public void Purchase2PriceIs(){
		Assert.assertEquals(450, this.purchases.get(2).getTotalPrice().intValue());
	}

	@Test
	public void Purchase3PriceIs(){
		Assert.assertEquals(1050, this.purchases.get(3).getTotalPrice().intValue());
	}
}
