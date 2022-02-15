package com.learning.spring.dao;

import com.learning.spring.models.Report;
import com.learning.spring.models.Review;
import com.learning.spring.models.Student;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
public class ReviewDAO {
    private final Logger LOGGER = Logger.getLogger(ReviewDAO.class);

    public List<Review> showAll() {
        return null;
    }

    public Review showAllInfo(Integer id) {
        return null;
    }

    public Boolean update(Integer id, Review review) {
        return true;
    }

    public Boolean delete(Integer id) {
        return true;
    }

    public void save(Review review) {


    }

    public static Review parseReview(ResultSet resultSet) throws SQLException {
        return new Review(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getDate(5));
    }
}
