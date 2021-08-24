package tp.persistence.mysql;

import tp.model.items.BaseItem;
import tp.model.items.CompoundItem;
import tp.model.utils.ItemComposition;
import tp.persistence.repositories.item.ItemCompositionDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ItemCompositionSQLDAO implements ItemCompositionDAO {
    @Override
    public Collection<ItemComposition> findAll() {
        String sqlString = "SELECT * FROM itemComposition";

        List<ItemComposition> result = new ArrayList<>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                ItemComposition itemComposition = new ItemComposition(
                        resultSet.getString("id"),
                        (resultSet.getString("baseItemId").isEmpty() ? null : new BaseItem(resultSet.getString("baseItemId"), null, null, null)),
                        (resultSet.getString("compoundId").isEmpty() ? null : new CompoundItem(resultSet.getString("compoundId"), null, null))
                        );
                result.add(itemComposition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<ItemComposition> findById(String id) {
        String sqlString = "SELECT * FROM itemComposition WHERE id = ?";

        List<ItemComposition> result = new ArrayList<>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                ItemComposition itemComposition = new ItemComposition(
                        resultSet.getString("id"),
                        (resultSet.getString("baseItemId").isEmpty() ? null : new BaseItem(resultSet.getString("baseItemId"), null, null, null)),
                        (resultSet.getString("compoundId").isEmpty() ? null : new CompoundItem(resultSet.getString("compoundId"), null, null))
                );
                result.add(itemComposition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Optional<ItemComposition> update(ItemComposition itemComposition) {
        String sqlString = "UPDATE itemComposition SET " +
                "baseItemId = ?, " +
                "compoundId = ? " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, itemComposition.getBaseItem().getId().toString());
            pstmt.setString(2, itemComposition.getCompoundItem().getId().toString());
            pstmt.setString(3, itemComposition.getId());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(itemComposition);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<ItemComposition> insert(ItemComposition itemComposition) {
        String sqlString = "INSERT INTO itemComposition " +
                "(id, " +
                "baseItemId, " +
                "compoundId" +
                "VALUES (" +
                "?, " +
                "?, " +
                "?" +
                ")";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, itemComposition.getBaseItem().getId().toString());
            pstmt.setString(2, itemComposition.getCompoundItem().getId().toString());
            pstmt.setString(3, itemComposition.getId());
            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(itemComposition);
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
