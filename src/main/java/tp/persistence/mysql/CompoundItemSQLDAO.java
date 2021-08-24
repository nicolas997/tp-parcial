package tp.persistence.mysql;

import tp.model.items.CompoundItem;
import tp.persistence.repositories.item.CompoundItemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CompoundItemSQLDAO implements CompoundItemDAO {
    @Override
    public Collection<CompoundItem> findAll() {
        String sqlString = "SELECT * FROM compoundItem";

        List<CompoundItem> result = new ArrayList<>();
        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                CompoundItem compoundItem = new CompoundItem(
                        resultSet.getString("id"),
                        (resultSet.getString("fatherId").isEmpty() ? null : new CompoundItem(resultSet.getString("fatherId"), null, null)),
                        null
                );
                result.add(compoundItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<CompoundItem> findById(String id) {
        String sqlString = "SELECT * FROM compoundItem WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                CompoundItem compoundItem = new CompoundItem(
                        resultSet.getString("id"),
                        (resultSet.getString("fatherId").isEmpty() ? null : new CompoundItem(resultSet.getString("fatherId"), null, null)),
                        null
                );
                return Optional.of(compoundItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<CompoundItem> update(CompoundItem compoundItem) {
        String sqlString = "UPDATE compoundItem SET " +
                "fatherId = ?, " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, compoundItem.getBaseItem().getId().toString());
            pstmt.setString(2, compoundItem.getId());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(compoundItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<CompoundItem> insert(CompoundItem compoundItem) {
        String sqlString = "INSERT INTO compoundItem " +
                "(id, " +
                "fatherId) " +
                "VALUES (" +
                "?, " +
                "?" +
                ")";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            pstmt.setString(1, compoundItem.getId());
            pstmt.setString(2, compoundItem.getBaseItem().getId().toString());

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(compoundItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<CompoundItem> deleteById(String id) {
        String sqlString = "DELETE FROM compoundItem WHERE id = ?";

        try (PreparedStatement pstmt = MySQLConnection.getConnection().prepareStatement(sqlString)) {
            Optional<CompoundItem> optCompoundItem = this.findById(id);
            CompoundItem compoundItem = optCompoundItem.get();

            pstmt.setString(1, id);

            int affected = pstmt.executeUpdate();

            if (affected > 0) return Optional.of(compoundItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }
}
