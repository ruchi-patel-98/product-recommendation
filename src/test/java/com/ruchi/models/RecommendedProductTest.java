package com.ruchi.models;

import com.ruchi.utils.PropertyReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecommendedProductTest {

    @Before
    public void setup() {
        PropertyReader.initializeProperties();
    }

    @Test
    public void testGetKeywordCount() {

        RecommendedProduct rp = new RecommendedProduct();

        List<String> testList = new ArrayList<String>();
        testList.add("perfect product.");
        testList.add("this is bad review. do not buy this product");
        rp.setReviewList(testList);
        assertEquals(1, rp.getKeywordCount(PropertyReader.POSITIVE_KEYWORDS));
        assertEquals(2, rp.getKeywordCount(PropertyReader.NEGATIVE_KEYWORDS));
    }

    @Test
    public void testCompareTo() {

        RecommendedProduct rp1 = new RecommendedProduct();
        List<String> testList1 = new ArrayList<String>();
        testList1.add("perfect product.");
        rp1.setReviewList(testList1);
        rp1.setOverallRatingRange(5);
        rp1.setAverageOverallRating(4.5f);


        RecommendedProduct rp2 = new RecommendedProduct();
        List<String> testList2 = new ArrayList<String>();
        testList2.add("this is bad review. do not buy this product");
        rp2.setReviewList(testList2);
        rp1.setTotalReviewCount(100);
        rp2.setOverallRatingRange(5);
        rp2.setAverageOverallRating(4.5f);
        rp2.setTotalReviewCount(100);


        assertEquals(1, rp1.compareTo(rp2));
        assertEquals(-1, rp2.compareTo(rp1));
    }

}
