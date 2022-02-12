package com.learning.spring.dao;

import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.Student;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class ConnectingStudentDAO {
    private final Logger LOGGER = Logger.getLogger(ConnectingStudentDAO.class);

    public List<ConnectingStudent> showAll(){
        return null;
    }
    public ConnectingStudent showAllInfo(Integer id){
        return null;
    }
    public Boolean update(Integer id, ConnectingStudent connectingStudent){
        return true;
    }
    public Boolean delete(Integer id){
        return true;
    }

    public void save(ConnectingStudent connectingStudent) {

    }
    public static ConnectingStudent parseConnectingStudent (ResultSet resultSet) throws SQLException {
        return new ConnectingStudent(
                resultSet.getInt(1),
                resultSet.getInt(2));
    }
}
