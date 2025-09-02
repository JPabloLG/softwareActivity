package uniquindio.edu.co.DAO;

import uniquindio.edu.co.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserDAOImp implements UserDAO {

    private final Connection connection;

    public UserDAOImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(UserDTO user) {
        String sql = "INSERT INTO user (id, name, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO findById(int id) {
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return null;
    }

    @Override
    public LinkedList<UserDTO> findAll() {
        return null;
    }

    @Override
    public void update(UserDTO user) {

    }

    @Override
    public void delete(int id) {

    }
}
