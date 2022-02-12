package com.learning.spring.dao;

import com.learning.spring.models.Admin;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
public class AdminDAO {
    private final Logger LOGGER = Logger.getLogger(AdminDAO.class);

    public List<Admin> showAll(){
        return null;
    }
    public Admin showAllInfo(Integer id){
        Admin admin = null;
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM ADMIN where id = ?");
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            admin = new Admin(
                    resultSet.getInt("admin_id"),
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("patronymic"),
                    resultSet.getString("surname"));

        } catch (SQLException e) {

        }
        return admin;
    }
    public Boolean update(Integer id, Admin admin){
        return true;
    }
    public Boolean delete(Integer id){
        return true;
    }

    public void save(Admin admin) {

    }
}
