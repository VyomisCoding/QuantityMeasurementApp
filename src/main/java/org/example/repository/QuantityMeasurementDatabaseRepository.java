package org.example.repository;

import org.example.entity.QuantityMeasurementEntity;
import org.example.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    public QuantityMeasurementDatabaseRepository(){
        createTableIfNotExists();
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO measurements (operation, result) VALUES (?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getOperation());
            ps.setString(2, entity.getResult());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving to DB", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM measurements";

        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new QuantityMeasurementEntity(
                        rs.getString("operation"),
                        rs.getString("result")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching data");
        }

        return list;
    }
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS measurements (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "operation VARCHAR(50), " +
                "result VARCHAR(255))";

        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException("Error creating table");
        }
    }
}