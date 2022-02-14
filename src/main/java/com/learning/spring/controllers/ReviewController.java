package com.learning.spring.controllers;

import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.models.Review;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operation/review")
public class ReviewController {

    private final ReviewDAO reviewDAO;

    private final Logger LOGGER = Logger.getLogger(ReviewDAO.class);

    @Autowired
    public ReviewController(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithReview(Model model) {
        model.addAttribute("reviews", reviewDAO.showAll());
        model.addAttribute("newReview", new Review());

        LOGGER.debug("Show all Review");

        return "reviews/operationsOnReview";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showReviewIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("review", reviewDAO.showAllInfo(id));
        LOGGER.debug("Show Review with " + id);

        return "reviews/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editReview(@PathVariable("id") int id, Model model) {
        Review review = reviewDAO.showAllInfo(id);
        model.addAttribute("review", review);
        LOGGER.debug("Show Review  " + review.toString());

        return "reviews/editReview";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewReview(@ModelAttribute("review") Review review) {

        reviewDAO.save(review);

        LOGGER.debug("Save new Review" + review.toString());


        return "redirect:/operation/review";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateReview(@ModelAttribute("review") Review review, @PathVariable("id") int id) {
        LOGGER.debug("Update Review" + review.toString());

        reviewDAO.update(id, review);

        return "redirect:/operation/review";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteReview(@PathVariable("id") int id) {
        LOGGER.debug("Delete Review N" + id);

        reviewDAO.delete(id);

        return "redirect:/operation/review";
    }
}
