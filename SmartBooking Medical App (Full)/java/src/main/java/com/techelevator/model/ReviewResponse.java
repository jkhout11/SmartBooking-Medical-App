package com.techelevator.model;

public class ReviewResponse {

    private int responseId;
    private int reviewId;
    private int responder;
    private String responseContent;

    public ReviewResponse() { }

    public ReviewResponse(int responseId, int reviewId, int responder, String responseContent){
        this.responseId = responseId;
        this.reviewId = reviewId;
        this.responder = responder;
        this.responseContent = responseContent;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getResponder() {
        return responder;
    }

    public void setResponder(int responder) {
        this.responder = responder;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }
}
