package tp.persistence.mysql;

import tp.model.client.Client;
import tp.model.client.notification.NotificationChannel;
import tp.model.items.BaseItem;
import tp.model.items.CompoundItem;
import tp.model.utils.Country;
import tp.model.utils.ResultSetUtils;
import tp.persistence.repositories.item.BaseItemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BaseItemSQLDAO implements BaseItemDAO {
    @Override
    public Collection<BaseItem> findAll() {
        String sqlString = "SELECT * FROM item";

        List<BaseItem> result = new ArrayList<>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                BaseItem baseItem = new BaseItem(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        new CompoundItem(resultSet.getString("compoundId"), null, null)
                        );
                result.add(baseItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<BaseItem> findById(String id) {
        String sqlString = "SELECT * FROM item WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                BaseItem baseItem = new BaseItem(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        new CompoundItem(resultSet.getString("compoundId"), null, null)
                );
                return Optional.of(baseItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<BaseItem> update(BaseItem baseItem) {
        String sqlString = "UPDATE client SET " +
                "name = ?, " +
                "price = ?, " +
                "compoundId = ?, " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, baseItem.getName());
            pstmt.setBigDecimal(2, baseItem.getPrice());
            pstmt.setString(3, baseItem.getCompoundItem().getName());

            pstmt.setString(4, baseItem.getId());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(baseItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<BaseItem> insert(BaseItem baseItem) {
        String sqlString = "INSERT INTO item " +
                "(id, " +
                "name, " +
                "price, " +
                "compoundId) " +
                "VALUES (" +
                "?, " +
                "?, " +
                "?, " +
                "?" +
                ")";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, baseItem.getId());
            pstmt.setString(2, baseItem.getName());
            pstmt.setBigDecimal(3, baseItem.getPrice());
            pstmt.setString(4, baseItem.getCompoundItem().getName());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(baseItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<BaseItem> deleteById(String id) {
        String sqlString = "DELETE FROM item WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            Optional<BaseItem> optBaseItem = this.findById(id);
            BaseItem baseItem = optBaseItem.get();

            pstmt.setString(1, id);

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(baseItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }

}
