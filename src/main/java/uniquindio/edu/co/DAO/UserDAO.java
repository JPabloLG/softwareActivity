package uniquindio.edu.co.DAO;

import uniquindio.edu.co.DTO.UserDTO;

import java.util.LinkedList;

public interface UserDAO {
    void save(UserDTO user);
    UserDTO findById(int id);
    UserDTO findByEmail(String email);
    LinkedList<UserDTO> findAll();
    void update(UserDTO user);
    void delete(int id);
}
