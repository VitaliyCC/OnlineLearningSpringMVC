package com.learning.spring.dao;

import com.learning.spring.models.Report;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportDAO {
    private final Logger LOGGER = Logger.getLogger(ReportDAO.class);

    public List<Report> showAll(){
        return null;
    }
    public Report showAllInfo(Integer id){
        return null;
    }
    public Boolean update(Integer id, Report admin){
        return true;
    }
    public Boolean delete(Integer id){
        return true;
    }

    public void save(Report report) {


    }
}
