package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ReviewDto {
    private int reviewId;
    private int reviewer;
    private String officeName;
    private String username;
    private int reviewRating;
    private String reviewTitle;
    private String reviewContent;
    private LocalDate reviewDate;

    public ReviewDto(){};

    public ReviewDto(int reviewId, int reviewer, String officeName, String username, int reviewRating, String reviewTitle, String reviewContent, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.reviewer = reviewer;
        this.officeName = officeName;
        this.username = username;
        this.reviewRating = reviewRating;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getReviewer() {
        return reviewer;
    }

    public void setReviewer(int reviewer) {
        this.reviewer = reviewer;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
