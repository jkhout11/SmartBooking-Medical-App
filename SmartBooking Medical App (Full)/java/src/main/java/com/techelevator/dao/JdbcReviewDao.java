package com.techelevator.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import com.techelevator.model.ReviewDto;
import com.techelevator.model.ReviewResponse;
import com.techelevator.model.User;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Review> getAllReviews() {
        List<Review> listOfReviews = new ArrayList<>();
        String sql = "SELECT review_id, reviewer, office_name, username, review_rating, review_title, review_content, review_date " +
                "FROM review " +
                "JOIN users ON review.reviewer = users.user_id " +
                "JOIN office ON review.reviewed_office = office.office_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Review review = mapRowToReview(results);
                listOfReviews.add(review);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
            return listOfReviews;

    }

    @Override
    public Review getReviewById(int id) {
        Review review = null;
        String sql = "SELECT review_id, reviewer, office_name, username, review_rating, review_title, review_content, review_date FROM review " +
                "JOIN users ON review.reviewer = users.user_id " +
                "JOIN office ON review.reviewed_office = office.office_id " +
                "WHERE review_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                review = mapRowToReview(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return review;

    }

    @Override
    public List<ReviewDto> getReviewsByReviewedOffice(int officeId) {
        List<ReviewDto> reviewsByOffice = new ArrayList<>();
        String sql = "SELECT review_id, reviewer, office_name, username, review_rating, review_title, review_content, review_date FROM review " +
                "JOIN users ON review.reviewer = users.user_id " +
                "JOIN office ON review.reviewed_office = office.office_id " +
                "WHERE office.office_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, officeId);
            while (results.next()) {
                ReviewDto review = mapRowToReviewDto(results);
                reviewsByOffice.add(review);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return reviewsByOffice;
    }

    @Override
    public Review createReview(Review review, Principal principal) {
        Review newReview = null;
        String sql = "INSERT INTO review (reviewer, review_rating, " +
                "review_content, review_date, reviewed_office, review_title) values (?, ?, ?, ?, ?, ?) RETURNING review_id;";

        try {
            int newReviewId = jdbcTemplate.queryForObject(sql, Integer.class, review.getReviewer(), review.getReviewRating(), review.getReviewContent(), review.getReviewDate(), review.getReviewedOffice(), review.getReviewTitle());
            newReview = getReviewById(newReviewId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newReview;
    }

    @Override
    public Review updateReview(Review review) {
        Review updatedReview;
        String sql = "UPDATE review SET review_id = ?, reviewer = ?, review_rating = ?, " +
        "review_content = ?, review_date = ?, reviewed_office = ?, review_title = ? WHERE review_id = ?";
        try {
            int updatedReviewId = jdbcTemplate.queryForObject(sql, Integer.class, review.getReviewId() ,review.getReviewer(), review.getReviewRating(), review.getReviewContent(), review.getReviewDate(), review.getReviewedOffice(), review.getReviewTitle(), review.getUsername());
            updatedReview = getReviewById(updatedReviewId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedReview;
    }

    @Override
    public ReviewResponse getReviewResponseById(int id) {
        ReviewResponse reviewResponse = null;
        String sql = "SELECT response_id, review_id, responder, response_content " +
                "FROM review_response WHERE response_id = ?";
            try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                reviewResponse = mapRowToReviewResponse(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return reviewResponse;
    }

    @Override
    public ReviewResponse reviewResponse(ReviewResponse reviewResponse) {
        ReviewResponse newReviewResponse = null;
        String sql = "INSERT INTO review_response (review_id, responder, response_content) values (?, ?, ?) RETURNING response_id";

        try {
            int newReviewResponseId = jdbcTemplate.queryForObject(sql, int.class, reviewResponse.getReviewId(), reviewResponse.getResponder(), reviewResponse.getResponseContent());
            newReviewResponse = getReviewResponseById(newReviewResponseId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newReviewResponse;
    }

    private Review mapRowToReview(SqlRowSet rs){
        return null;
    }
    private ReviewDto mapRowToReviewDto(SqlRowSet rs) {
        ReviewDto review = new ReviewDto();
        review.setReviewId(rs.getInt("review_id"));
        review.setReviewer(rs.getInt("reviewer"));
        review.setOfficeName(rs.getString("office_name"));
        review.setUsername(rs.getString("username"));
        review.setReviewRating(rs.getInt("review_rating"));
        review.setReviewTitle(rs.getString("review_title"));
        review.setReviewContent(rs.getString("review_content"));
        review.setReviewDate(rs.getDate("review_date").toLocalDate());

        return review;
    }

    private ReviewResponse mapRowToReviewResponse(SqlRowSet rs) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setResponseId(rs.getInt("response_id"));
        reviewResponse.setReviewId(rs.getInt("review_id"));
        reviewResponse.setResponder(rs.getInt("responder"));
        reviewResponse.setResponseContent(rs.getString("response_content"));

        return reviewResponse;
    }
}
