package tp.persistence.mysql;

import tp.model.client.Client;
import tp.model.client.notification.NotificationChannel;
import tp.model.utils.Country;
import tp.model.utils.ResultSetUtils;
import tp.persistence.repositories.client.ClientDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

public class ClientSQLDAO implements ClientDAO {

    @Override
    public Collection<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findById(String id) {
        String sqlString = "SELECT * FROM client WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Client client = new Client(
                        resultSet.getString("id"),
                        Country.valueOf(resultSet.getString("country")),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getTimestamp("registrationDate").toInstant(),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("lastPurchaseDate")),
                        NotificationChannel.fromName(NotificationChannel.NotificationChannelName.valueOf(resultSet.getString("notificationChannel"))),
                        resultSet.getString("formattedAddress"),
                        resultSet.getFloat("addressLatitude"),
                        resultSet.getFloat("addressLongitude")
                );

                return Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client client) {
        String sqlString = "UPDATE client SET " +
                "country = ?, " +
                "email = ?, " +
                "phoneNumber = ?, " +
                "registrationDate = ?, " +
                "lastPurchaseDate = ?, " +
                "notificationChannel = ? " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, client.getCountry().name());
            pstmt.setString(2, client.getEmail());
            pstmt.setString(3, client.getPhoneNumber());
            pstmt.setTimestamp(4, Timestamp.from(client.getRegistrationDate()));
            pstmt.setTimestamp(5, client.getLastPurchaseDate() != null ? Timestamp.from(client.getLastPurchaseDate()) : null);
            pstmt.setString(6, client.getNotificationChannel().getName().name());
            pstmt.setString(7, client.getId());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Client> insert(Client client) {
        String sqlString = "INSERT INTO client " +
                            "(id, " +
                            "country, " +
                            "email, " +
                            "phoneNumber, " +
                            "registrationDate, " +
                            "lastPurchaseDate, " +
                            "notificationChannel) " +
                            "VALUES (" +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?" +
                            ")";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, client.getId());
            pstmt.setString(2, client.getCountry().name());
            pstmt.setString(3, client.getEmail());
            pstmt.setString(4, client.getPhoneNumber());
            pstmt.setTimestamp(5, Timestamp.from(client.getRegistrationDate()));
            pstmt.setTimestamp(6, client.getLastPurchaseDate() != null ? Timestamp.from(client.getLastPurchaseDate()) : null);
            pstmt.setString(7, client.getNotificationChannel().getName().name());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Client> deleteById(String id) {
        return Optional.empty();
    }

}
