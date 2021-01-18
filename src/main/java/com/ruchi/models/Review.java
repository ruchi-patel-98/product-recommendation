package com.ruchi.models;

import java.util.List;

public class Review {
    private ReviewStatistics reviewStatistics;
    private List<String> reviewTexts;

    public ReviewStatistics getReviewStatistics() {
        return reviewStatistics;
    }

    public void setReviewStatistics(ReviewStatistics reviewStatistics) {
        this.reviewStatistics = reviewStatistics;
    }

    public List<String> getReviewTexts() {
        return reviewTexts;
    }

    public void setReviewTexts(List<String> reviewTexts) {
        this.reviewTexts = reviewTexts;
    }
}
