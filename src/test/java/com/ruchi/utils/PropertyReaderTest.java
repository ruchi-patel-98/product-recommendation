package com.ruchi.utils;

import junit.framework.*;
import com.ruchi.utils.PropertyReader;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PropertyReaderTest {

    @Test
    public void testInitializeProperties() {
        PropertyReader.initializeProperties();
        assertNotNull(PropertyReader.API_URL);
        assertNotNull(PropertyReader.API_KEY);
        assertNotNull(PropertyReader.SEARCH_ENDPOINT);
        assertNotNull(PropertyReader.RECOMMENDATION_ENDPOINT);
        assertNotNull(PropertyReader.REVIEW_ENDPOINT);
        assertNotNull(PropertyReader.DEBUG);
        assertNotNull(PropertyReader.POSITIVE_KEYWORDS);
        assertNotNull(PropertyReader.NEGATIVE_KEYWORDS);
    }
}
