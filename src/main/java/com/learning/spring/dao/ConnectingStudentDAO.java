package com.learning.spring.dao;

import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.Student;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

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
}
