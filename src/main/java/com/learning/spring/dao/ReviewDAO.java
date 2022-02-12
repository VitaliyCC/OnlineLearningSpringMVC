package com.learning.spring.dao;

import com.learning.spring.models.Review;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewDAO {
    private final Logger LOGGER = Logger.getLogger(ReviewDAO.class);

    public List<Review> showAll(){
        return null;
    }
    public Review showAllInfo(Integer id){
        return null;
    }
    public Boolean update(Integer id, Review review){
        return true;
    }
    public Boolean delete(Integer id){
        return true;
    }

    public void save(Review review) {


    }
}
