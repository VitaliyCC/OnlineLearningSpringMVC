package com.learning.spring.dao;

import com.learning.spring.models.Admin;
import com.learning.spring.models.Subject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectDAO {
    private final Logger LOGGER = Logger.getLogger(SubjectDAO.class);

    public List<Subject> showAll(){
        return null;
    }
    public Subject showAllInfo(Integer id){
        return null;
    }
    public Boolean update(Integer id, Subject subject){
        return true;
    }
    public Boolean delete(Integer id){
        return true;
    }

    public void save(Subject subject) {

    }
}
