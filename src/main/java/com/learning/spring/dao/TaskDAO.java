package com.learning.spring.dao;

import com.learning.spring.models.Task;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TaskDAO {
    private final Logger LOGGER = Logger.getLogger(TaskDAO.class);

    public List<Task> showAll(){
        return null;
    }
    public Task showAllInfo(Integer id){
        return null;
    }
    public Boolean update(Integer id, Task task){
        return true;
    }
    public Boolean delete(Integer id){
        return true;
    }

    public void save(Task task) {

    }
}
