package tp.persistence.mysql;

import tp.model.client.Client;
import tp.model.client.notification.NotificationChannel;
import tp.model.utils.Country;
import tp.model.utils.ResultSetUtils;
import tp.persistence.repositories.client.ClientDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

public class ClientSQLDAO implements ClientDAO {

    @Override
    public Collection<Client> findAll() {
        String sqlString = "SELECT * FROM client";

        List<Client> result = new ArrayList<>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Client client = new Client(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        Country.valueOf(resultSet.getString("country")),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getTimestamp("registrationDate").toInstant(),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("lastPurchaseDate")),
                        NotificationChannel.fromName(NotificationChannel.NotificationChannelName.valueOf(resultSet.getString("notificationChannel"))),
                        resultSet.getString("formattedAdress"),
                        resultSet.getFloat("addressLatitude"),
                        resultSet.getFloat("addressLongitude")
                );
                result.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
                        resultSet.getString("name"),
                        Country.valueOf(resultSet.getString("country")),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getTimestamp("registrationDate").toInstant(),
                        ResultSetUtils.timestampToInstant(resultSet.getTimestamp("lastPurchaseDate")),
                        NotificationChannel.fromName(NotificationChannel.NotificationChannelName.valueOf(resultSet.getString("notificationChannel"))),
                        resultSet.getString("formattedAdress"),
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
                "name = ?, " +
                "country = ?, " +
                "email = ?, " +
                "password = ?, " +
                "phoneNumber = ?, " +
                "registrationDate = ?, " +
                "lastPurchaseDate = ?, " +
                "notificationChannel = ?, " +
                "formattedAdress = ?, " +
                "addressLatitude = ?, " +
                "addressLongitude = ?" +
                "WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getCountry().name());
            pstmt.setString(3, client.getEmail());
            pstmt.setString(4, client.getPassword());
            pstmt.setString(5, client.getPhoneNumber());
            pstmt.setTimestamp(6, Timestamp.from(client.getRegistrationDate()));
            pstmt.setTimestamp(7, client.getLastPurchaseDate() != null ? Timestamp.from(client.getLastPurchaseDate()) : null);
            pstmt.setString(8, client.getNotificationChannel().getName().name());
            pstmt.setString(9, client.getDisplayableAddress());
            pstmt.setFloat(10, client.getAddressLatitude());
            pstmt.setFloat(11, client.getAddressLongitude());
            pstmt.setString(12, client.getId());

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
                            "name, " +
                            "country, " +
                            "email, " +
                            "password, " +
                            "phoneNumber, " +
                            "registrationDate, " +
                            "lastPurchaseDate, " +
                            "notificationChannel, " +
                            "formattedAdress, " +
                            "addressLatitude, " +
                            "addressLongitude) " +
                            "VALUES (" +
                            "?, " +
                            "?, " +
                            "?, " +
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
            pstmt.setString(1, client.getId());
            pstmt.setString(2, client.getName());
            pstmt.setString(3, client.getCountry().name());
            pstmt.setString(4, client.getEmail());
            pstmt.setString(5, client.getPassword());
            pstmt.setString(6, client.getPhoneNumber());
            pstmt.setTimestamp(7, Timestamp.from(client.getRegistrationDate()));
            pstmt.setTimestamp(8, client.getLastPurchaseDate() != null ? Timestamp.from(client.getLastPurchaseDate()) : null);
            pstmt.setString(9, client.getNotificationChannel().getName().name());
            pstmt.setString(10, client.getDisplayableAddress());
            pstmt.setFloat(11, client.getAddressLatitude());
            pstmt.setFloat(12, client.getAddressLongitude());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Client> deleteById(String id) {
        String sqlString = "DELETE FROM client WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            Optional<Client> optClient = this.findById(id);
            Client client = optClient.get();

            pstmt.setString(1, id);

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
        
    }

}
