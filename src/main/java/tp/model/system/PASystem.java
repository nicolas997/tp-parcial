package tp.model.system;

import tp.model.client.Client;
import tp.model.organization.Organization;
import tp.model.purchase.Purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PASystem {

	List<Organization> organizations;
	List<Purchase> purchases;

	private static PASystem instance = null;

	private PASystem(){}

	public static PASystem getInstance() {
		if (instance == null) {
			instance = new PASystem();
			instance.organizations = new ArrayList<>();
			instance.purchases = new ArrayList<>();
		}
		return instance;
	}

	public void listAllItems() {
		this.organizations.forEach(Organization :: listItems);
	}

	public void listAllOrganizations() {
		this.organizations.forEach(org -> System.out.println(org.toString()));
	}

	public void suscribeClientToPurchase(Client client, String purchaseId) {
		Purchase objective = this.purchases.
				stream().
				filter(purch -> purch.getId().equals(purchaseId)).findFirst().
				orElseThrow(() -> new RuntimeException("purchase " + purchaseId + " was not found, try again"));

		objective.addObserver(client.getPurchaseObserver());
	}
}
