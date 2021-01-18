package com.ruchi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruchi.models.RecommendedProduct;
import org.junit.Test;
import org.mockito.Mockito;

import static com.ruchi.utils.ReadFileUtil.readFile;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RequestTaskTest {

    @Test
    public void testCall() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        RecommendedProduct recommendedProduct = new RecommendedProduct();
        recommendedProduct.setItemId(123l);
        recommendedProduct.setItemName("XBox");

        URLUtils mockUtils = Mockito.mock(URLUtils.class);

        String sampleReviewResponse = readFile("sample-review-response.json");
        when(mockUtils.getAPIURL("reviews", "123"))
            .thenReturn("http://someurl");
        when(mockUtils.getJSONResponse("http://someurl"))
            .thenReturn(sampleReviewResponse);

        RequestTask requestTask = new RequestTask(recommendedProduct, objectMapper, mockUtils);

        RecommendedProduct actual = requestTask.call();

        assertEquals(200, actual.getTotalReviewCount());
        assertEquals(4.0f, actual.getOverallRatingRange(), 0.0f);
        assertEquals(4.5f, actual.getAverageOverallRating(), 0.0f);

    }
}
