package tp.persistence.mysql;

import tp.model.client.Client;
import tp.model.client.notification.NotificationChannel;
import tp.model.rider.Rider;
import tp.model.utils.Country;
import tp.model.utils.ResultSetUtils;
import tp.persistence.repositories.rider.RiderDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class RiderSQLDAO implements RiderDAO {
    @Override
    public Collection<Rider> findAll() {
        String sqlString = "SELECT * FROM rider";

        List<Rider> result = new ArrayList<>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Rider rider = new Rider(
                        resultSet.getString("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        Country.valueOf(resultSet.getString("country")),
                        resultSet.getString("idType"),
                        resultSet.getString("idNumber"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("registrationDate").toInstant()
                );
                result.add(rider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Rider> findById(String id) {
        String sqlString = "SELECT * FROM rider WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Rider rider = new Rider(
                        resultSet.getString("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        Country.valueOf(resultSet.getString("country")),
                        resultSet.getString("idType"),
                        resultSet.getString("idNumber"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("registrationDate").toInstant()
                );

                return Optional.of(rider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Rider> update(Rider rider) {
        String sqlString = "UPDATE rider SET " +
                "firstName = ?, " +
                "lastName = ?, " +
                "country = ?, " +
                "idType = ?, " +
                "idNumber = ?, " +
                "email = ?, " +
                "password = ?, " +
                "registrationDate = ?, " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, rider.getFirstName());
            pstmt.setString(2, rider.getLastName());
            pstmt.setString(3, rider.getCountry().name());
            pstmt.setString(4, rider.getIdType());
            pstmt.setString(5, rider.getIdNumber());
            pstmt.setString(6, rider.getEmail());
            pstmt.setString(7, rider.getPassword());
            pstmt.setTimestamp(8, Timestamp.from(rider.getRegistrationDate()));
            pstmt.setString(9, rider.getId());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(rider);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Rider> insert(Rider rider) {
        String sqlString = "INSERT INTO rider " +
                "(id, " +
                "firstName, " +
                "lastName, " +
                "country, " +
                "idType, " +
                "idNumber, " +
                "email, " +
                "password, " +
                "registrationDate) " +
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
            pstmt.setString(1, rider.getId());
            pstmt.setString(2, rider.getFirstName());
            pstmt.setString(3, rider.getLastName());
            pstmt.setString(4, rider.getCountry().name());
            pstmt.setString(5, rider.getIdType());
            pstmt.setString(6, rider.getIdNumber());
            pstmt.setString(7, rider.getEmail());
            pstmt.setString(8, rider.getPassword());
            pstmt.setTimestamp(9, Timestamp.from(rider.getRegistrationDate()));

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(rider);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Rider> deleteById(String id) {
        String sqlString = "DELETE FROM rider WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            Optional<Rider> optRider = this.findById(id);
            Rider rider = optRider.get();

            pstmt.setString(1, id);

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(rider);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }
}
