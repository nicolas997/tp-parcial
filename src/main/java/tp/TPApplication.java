package tp;

import com.mashape.unirest.http.utils.URLParamEncoder;
import tp.external.google.GoogleApiClient;
import tp.external.google.GoogleGeocodeResult;
import tp.model.client.Client;
import tp.model.client.notification.EmailNotificationChannel;
import tp.model.client.notification.PhoneNotificationChannel;
import tp.model.items.*;
import tp.model.items.factories.BaseItemFactory;
import tp.model.items.factories.CompoundItemFactory;
import tp.model.purchase.Purchase;
import tp.model.rider.Rider;
import tp.model.utils.Country;
import tp.persistence.inmemory.*;
import tp.persistence.mysql.ClientSQLDAO;
import tp.persistence.mysql.MySQLConnection;
import tp.persistence.repositories.client.ClientDAO;
import tp.persistence.repositories.item.BaseItemDAO;
import tp.persistence.repositories.item.CompoundItemDAO;
import tp.persistence.repositories.item.ItemDAO;
import tp.persistence.repositories.purchase.PurchaseDAO;
import tp.persistence.repositories.rider.RiderDAO;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

public class TPApplication {

    public static void main(String[] args) {

        //Alta de un cliente en la bd
        Client clientOne = new Client("juan", Country.ARG, "client-one@client.com", "pw1", "1154880546",
                EmailNotificationChannel.getInstance(), "9 de Julio, Buenos Aires");

        String clientId = clientOne.getId();

        ClientDAO dao = new ClientSQLDAO();
        dao.insert(clientOne);

        //Muestro un cliente de la bd
        Optional<Client> clientOpt = dao.findById(clientId);
        Client client = clientOpt.get();
        System.out.println(client);

        //Borro el cliente de la bd
        Optional<Client> clientOptTwo = dao.deleteById(clientId);


        //Caso de prueba de un pedido
        RiderDAO riderDAO                 = new RiderIMDAO();
        ClientDAO clientDAO               = new ClientIMDAO();
        BaseItemDAO baseItemDAO           = new BaseItemIMDAO();
        CompoundItemDAO compoundItemDAO   = new CompoundItemIMDAO();
        ItemDAO itemDAO                   = new ItemIMDAO(baseItemDAO, compoundItemDAO);
        PurchaseDAO purchaseDAO           = new PurchaseIMDAO();

        Rider riderOne = new Rider("RiderOne", "RiderOne",
                Country.ARG, "DNI", "35118479",
                "rider-one@rider.com", "pw2");
        Rider riderTwo = new Rider("RiderTwo", "RiderTwo",
                Country.ARG, "DNI", "380484776",
                "rider-one@rider.com", "pw3");

        riderDAO.insert(riderOne);
        riderDAO.insert(riderTwo);

        Client clientThree = new Client("roberto", Country.ARG, "client-one@client.com", "pw1", "1154880546",
                EmailNotificationChannel.getInstance(), "9 de Julio, Buenos Aires");
        Client clientTwo = new Client("ana", Country.ARG, "client-two@client.com", "pw4", "1150488073",
                PhoneNotificationChannel.getInstance(), "25 de Mayo, Buenos Aires, Argentina");

        clientDAO.insert(clientOne);
        clientDAO.insert(clientTwo);

        BaseItem smallCokeBottle = BaseItemFactory.build("Coke Bottle (small)", 120);
        BaseItem largeCokeBottle = BaseItemFactory.build("Coke Bottle (large)", 175);

        itemDAO.insert(smallCokeBottle);
        itemDAO.insert(largeCokeBottle);

        CompoundItem pepperoniPizza =   CompoundItemFactory.build(
                                            BaseItemFactory.build("Base Pizza", 450)
                                        )
                                        .addExtra(BaseItemFactory.build("Salame", 35))
                                        .addExtra(BaseItemFactory.build("Pimentón", 25))
                                        .addExtra(BaseItemFactory.build("Ají", 35));

        CompoundItem pepperoniPizzaExtraCheese =    CompoundItemFactory.build(pepperoniPizza)
                                                    .addExtra(BaseItemFactory.build("Extra Cheese", 58));

        itemDAO.insert(pepperoniPizza);
        itemDAO.insert(pepperoniPizzaExtraCheese);

        Purchase purchaseOne = new Purchase(clientOne, new Item[]{pepperoniPizzaExtraCheese, smallCokeBottle});

        purchaseOne.confirm();
        purchaseOne.dispatch(riderOne);
        purchaseOne.finishDelivery();

        purchaseDAO.insert(purchaseOne);

    }

}
