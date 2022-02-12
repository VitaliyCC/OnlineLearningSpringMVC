package com.learning.spring.dao;

import com.learning.spring.models.Task;
import com.learning.spring.models.Teacher;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherDAO {
    private final Logger LOGGER = Logger.getLogger(TeacherDAO.class);

    public List<Teacher> showAll(){
        return null;
    }
    public Teacher showAllInfo(Integer id){
        return null;
    }
    public Boolean update(Integer id, Teacher teacher){
        return true;
    }
    public Boolean delete(Integer id){
        return true;
    }
}
