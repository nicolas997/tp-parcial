package modelTest;

import tp.model.client.*;
import tp.model.client.notification.*;
import tp.model.items.*;
import tp.model.items.factories.*;
import tp.model.purchase.*;
import tp.model.purchase.observer.PurchaseObserver;
import tp.model.rider.*;

import org.junit.Before;
import tp.model.utils.Country;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseTest {

	protected List<Client> clients;
	protected List<Rider> riders;
	protected List<BaseItem> baseItems;
	protected List<CompoundItem> compoundItems;
	protected List<Purchase> purchases;

	protected BaseTest() {
		this.clients = new ArrayList<>();
		this.riders = new ArrayList<>();
		this.baseItems = new ArrayList<>();
		this.compoundItems = new ArrayList<>();
		this.purchases = new ArrayList<>();
	}

	@Before
	public void testInit(){
		this.clientsInit();
		this.ridersInit();
		this.itemsInit();
		this.purhcaseInit();
	}

	private void clientsInit() {
		Client[] newClients = {
				new Client("Adam", Country.ARG, "adam@gmail.com", "adamPwd", "12345678", EmailNotificationChannel.getInstance()/*, "Hidalgo 1171, Hurlingham, Provincia de Buenos Aires, Argentina"*/),
				new Client("Barbara", Country.ARG, "barbara@gmail.com", "barbaraPwd", "87654321", EmailNotificationChannel.getInstance()/*, "Corrientes 996, B1832 ELT, Provincia de Buenos Aires, Argentina"*/),
				new Client("Charles", Country.ARG, "charles@gmail.com", "charlesPwd", "13572468", PhoneNotificationChannel.getInstance()/*, "25 de Mayo 677, B1824NNC Lanús, Provincia de Buenos Aires, Argentina"*/),
				new Client("Danny", Country.ARG, "danny@gmail.com", "dannyPwd", "24681357", EmailNotificationChannel.getInstance()/*, "Av. Cabildo 272, C1426 AAP, Buenos Aires, Argentina"*/)
		};
		Collections.addAll(this.clients, newClients);
	}

	private void ridersInit() {
		Rider[] newRiders = {
				new Rider("Ema", "Alvarez", Country.ARG, "idType", "1", "ema@gmail.com", "emaPwd"),
				new Rider("Flynn", "Alvarez", Country.ARG, "idType", "2", "flynn@gmail.com", "flynnPwd"),
				new Rider("Grace", "Alvarez", Country.ARG, "idType", "3", "grace@gmail.com", "gracePwd"),
				new Rider("Homer", "Alvarez", Country.ARG, "idType", "4", "homer@gmail.com", "homerPwd"),
		};
		Collections.addAll(this.riders, newRiders);
	}

	private void itemsInit() {
		BaseItem[] newBaseItems = {
				BaseItemFactory.build("item10", 100),
				BaseItemFactory.build("item11", 200),
				BaseItemFactory.build("item12", 300),
				BaseItemFactory.build("item13", 400),
				BaseItemFactory.build("item14", 500),
				BaseItemFactory.build("item15", 600),
		};
		Collections.addAll(this.baseItems, newBaseItems);

		CompoundItem[] newCompoundItems = {
				CompoundItemFactory.build(this.baseItems.get(0)).addExtra(this.baseItems.get(1)),
				CompoundItemFactory.build(this.baseItems.get(2)).addExtra(this.baseItems.get(3)),
				CompoundItemFactory.build(this.baseItems.get(4)).addExtra(this.baseItems.get(5)),
		};
		Collections.addAll(this.compoundItems, newCompoundItems);

		this.compoundItems.add(CompoundItemFactory.build(this.baseItems.get(0)).addExtra(this.compoundItems.get(1)));
	}

	private void purhcaseInit() {
		Purchase[] newPurchases = {
				new Purchase(this.clients.get(0), new Item[]{this.baseItems.get(0), this.baseItems.get(1)}, BigDecimal.valueOf(50), new PurchaseObserver[]{}),
				new Purchase(this.clients.get(1), new Item[]{this.baseItems.get(2), this.baseItems.get(3)}, BigDecimal.valueOf(50),new PurchaseObserver[]{}),
				new Purchase(this.clients.get(2), new Item[]{this.baseItems.get(0), this.compoundItems.get(0)}, BigDecimal.valueOf(50),new PurchaseObserver[]{}),
				new Purchase(this.clients.get(3), new Item[]{this.baseItems.get(1), this.compoundItems.get(3)}, BigDecimal.valueOf(50),new PurchaseObserver[]{})
		};
		Collections.addAll(this.purchases, newPurchases);
	}
}
