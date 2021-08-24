package modelTest;

import org.junit.Assert;
import org.junit.Test;
import tp.model.purchase.Purchase;
import tp.model.purchase.status.ConfirmedStatus;
import tp.model.purchase.status.DeliveredStatus;
import tp.model.purchase.status.DispatchedStatus;
import tp.model.purchase.status.PendingStatus;

public class PurchaseStatusTest extends BaseTest {

	@Test
	public void PurchaseStatusPending(){
		Assert.assertEquals(new PendingStatus(), this.purchases.get(0).getStatus());
	}

	@Test
	public void PurchaseStatusConfirmed(){
		Purchase testSubject = this.purchases.get(0);
		testSubject.confirm();
		Assert.assertEquals(new ConfirmedStatus(), this.purchases.get(0).getStatus());
	}

	@Test
	public void PurchaseStatusDispatched(){
		Purchase testSubject = this.purchases.get(0);
		testSubject.confirm();
		testSubject.dispatch(this.riders.get(0));
		Assert.assertEquals(new DispatchedStatus(), this.purchases.get(0).getStatus());
	}

	@Test
	public void PurchaseStatusDelivered(){
		Purchase testSubject = this.purchases.get(0);
		testSubject.confirm();
		testSubject.dispatch(this.riders.get(0));
		testSubject.finishDelivery();
		Assert.assertEquals(new DeliveredStatus(), this.purchases.get(0).getStatus());
	}
}
