package tp.persistence.mysql;

import tp.model.items.BaseItem;
import tp.model.items.CompoundItem;
import tp.model.purchase.Purchase;
import tp.model.utils.ItemComposition;
import tp.model.utils.ItemXPurchase;
import tp.persistence.repositories.item.ItemXPurchaseDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ItemXPurchaseSQLDAO implements ItemXPurchaseDAO {
    @Override
    public Collection<ItemXPurchase> findAll() {
        String sqlString = "SELECT * FROM itemXPurchase";

        List<ItemXPurchase> result = new ArrayList<>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                ItemXPurchase itemXPurchase = new ItemXPurchase(
                        resultSet.getString("id"),
                        (resultSet.getString("itemId").isEmpty() ? null : new BaseItem(resultSet.getString("itemId"), null, null, null)),
                        (resultSet.getString("PurchaseId").isEmpty() ? null : new Purchase(resultSet.getString("PurchaseId"), null, null, null, null, null, null, null, null, null, null))
                );
                result.add(itemXPurchase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<ItemXPurchase> findById(String id) {
        String sqlString = "SELECT * FROM itemXPurchase WHERE id = ?";

        List<ItemXPurchase> result = new ArrayList<>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                ItemXPurchase itemXPurchase = new ItemXPurchase(
                        resultSet.getString("id"),
                        (resultSet.getString("itemId").isEmpty() ? null : new BaseItem(resultSet.getString("itemId"), null, null, null)),
                        (resultSet.getString("PurchaseId").isEmpty() ? null : new Purchase(resultSet.getString("PurchaseId"), null, null, null, null, null, null, null, null, null, null))
                );
                result.add(itemXPurchase);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Optional<ItemXPurchase> update(ItemXPurchase itemXPurchase) {
        String sqlString = "UPDATE itemXPurchase SET " +
                "itemId = ?, " +
                "purchaseId = ? " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, itemXPurchase.getItem().getId().toString());
            pstmt.setString(2, itemXPurchase.getPurchase().getId().toString());
            pstmt.setString(3, itemXPurchase.getId());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(itemXPurchase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<ItemXPurchase> insert(ItemXPurchase itemXPurchase) {
        String sqlString = "INSERT INTO itemXPurchase " +
                "(id, " +
                "itemId, " +
                "purchaseId" +
                "VALUES (" +
                "?, " +
                "?, " +
                "?" +
                ")";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, itemXPurchase.getId().toString());
            pstmt.setString(2, itemXPurchase.getItem().getId().toString());
            pstmt.setString(3, itemXPurchase.getPurchase().getId().toString());
            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(itemXPurchase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {
        String sqlString = "DELETE FROM itemComposition WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, id);

            int affected = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
