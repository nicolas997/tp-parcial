package tp.persistence.mysql;

import tp.model.client.Client;
import tp.model.client.notification.NotificationChannel;
import tp.model.items.Item;
import tp.model.purchase.Purchase;
import tp.model.purchase.status.*;
import tp.model.rider.Rider;
import tp.model.utils.Country;
import tp.model.utils.ResultSetUtils;
import tp.persistence.repositories.purchase.PurchaseDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PurchaseSQLDAO implements PurchaseDAO {
    @Override
    public Collection<Purchase> findAll() {
        String sqlString = "SELECT * FROM purchase";

        List<Purchase> result = new ArrayList<>();
        List<Item> items = new ArrayList<Item>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Purchase purchase = new Purchase(
                        resultSet.getString("id"),
                        new Client(resultSet.getString("client")),
                        new Rider(resultSet.getString("rider")),
                        resultSet.getBigDecimal("deliveryPrice"),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("creationDate")),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("confirmationDate")),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("dispatchDate")),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("deliveryDate")),
                        (   resultSet.getString("status").equals("pending") ? new PendingStatus() :
                            ((resultSet.getString("status").equals("confirmed")) ? new ConfirmedStatus() :
                                    ((resultSet.getString("status").equals("dispatched")) ? new DispatchedStatus() : new DeliveredStatus())
                            )
                        )
                );
                result.add(purchase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Purchase> findById(String id) {
        String sqlString = "SELECT * FROM purchase WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Purchase purchase = new Purchase(
                        resultSet.getString("id"),
                        new Client(resultSet.getString("client")),
                        new Rider(resultSet.getString("rider")),
                        resultSet.getBigDecimal("deliveryPrice"),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("creationDate")),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("confirmationDate")),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("dispatchDate")),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("deliveryDate")),
                        (   resultSet.getString("status").equals("pending") ? new PendingStatus() :
                                ((resultSet.getString("status").equals("confirmed")) ? new ConfirmedStatus() :
                                        ((resultSet.getString("status").equals("dispatched")) ? new DispatchedStatus() : new DeliveredStatus())
                                )
                        )
                );

                return Optional.of(purchase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Purchase> update(Purchase purchase) {
        String sqlString = "UPDATE purchase SET " +
                "client = ?, " +
                "rider = ?, " +
                "deliveryPrice = ?, " +
                "creationDate = ?, " +
                "confirmationDate = ?, " +
                "dispatchDate = ?, " +
                "deliveryDate = ?, " +
                "status = ?, " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, purchase.getClient().getId());
            pstmt.setString(2, purchase.getAssignedRider().getId());
            pstmt.setBigDecimal(3, purchase.getDeliveryPrice());
            pstmt.setTimestamp(4, Timestamp.from(purchase.getCreationDate()));
            pstmt.setTimestamp(5, Timestamp.from(purchase.getConfirmationDate()));
            pstmt.setTimestamp(6, Timestamp.from(purchase.getDispatchDate()));
            pstmt.setTimestamp(7, Timestamp.from(purchase.getDeliveryDate()));
            pstmt.setString(8, purchase.getStatus().toString());

            int affected = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Purchase> insert(Purchase purchase) {
        String sqlString = "INSERT INTO purchase " +
                "(id, " +
                "client, " +
                "rider, " +
                "deliveryPrice, " +
                "creationDate, " +
                "confirmationDate, " +
                "dispatchDate, " +
                "deliveryDate, " +
                "status) " +
                "VALUES (" +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "?" +
                ")";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, purchase.getId());
            pstmt.setString(2, purchase.getClient().getId());
            pstmt.setString(3, purchase.getAssignedRider().getId());
            pstmt.setBigDecimal(4, purchase.getDeliveryPrice());
            pstmt.setTimestamp(5, Timestamp.from(purchase.getCreationDate()));
            pstmt.setTimestamp(6, Timestamp.from(purchase.getConfirmationDate()));
            pstmt.setTimestamp(7, Timestamp.from(purchase.getDispatchDate()));
            pstmt.setTimestamp(8, Timestamp.from(purchase.getDeliveryDate()));
            pstmt.setString(9, purchase.getStatus().toString());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(purchase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Purchase> deleteById(String id) {
        String sqlString = "DELETE FROM purchase WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            Optional<Purchase> optPurchase = this.findById(id);
            Purchase purchase = optPurchase.get();

            pstmt.setString(1, id);

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(purchase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }
}
