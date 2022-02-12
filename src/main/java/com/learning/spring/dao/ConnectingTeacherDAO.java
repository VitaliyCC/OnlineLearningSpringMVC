package com.learning.spring.dao;

import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.ConnectingTeacher;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class ConnectingTeacherDAO {
    private final Logger LOGGER = Logger.getLogger(ConnectingTeacherDAO.class);

    public List<ConnectingTeacher> showAll(){
        return null;
    }
    public ConnectingTeacher showAllInfo(Integer id){
        return null;
    }
    public Boolean update(Integer id, ConnectingTeacher connectingTeacher){
        return true;
    }
    public Boolean delete(Integer id){
        return true;
    }

    public void save(ConnectingTeacher connectingTeacher) {

    }

    public static ConnectingTeacher parseConnectingTeacher (ResultSet resultSet) throws SQLException {
        return new ConnectingTeacher(
                resultSet.getInt(1),
                resultSet.getInt(2));
    }
}
