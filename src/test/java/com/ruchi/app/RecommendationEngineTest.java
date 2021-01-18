package com.ruchi.app;

import com.ruchi.models.Product;
import com.ruchi.utils.URLUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.ruchi.utils.ReadFileUtil.readFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class RecommendationEngineTest {

    private RecommendationEngine recommendationEngine;
    private URLUtils mockUtils;

    @Before
    public void setup() {
        mockUtils = Mockito.mock(URLUtils.class);
        recommendationEngine = new RecommendationEngine(mockUtils);
    }

    @Test
    public void testGetFirstItemId() throws Exception {

        String sampleSearchResponse = readFile("sample-search-response.json");
        when(mockUtils.getAPIURL("search", "ipad"))
            .thenReturn("http://someurl");
        when(mockUtils.getJSONResponse("http://someurl"))
            .thenReturn(sampleSearchResponse);

        Long actualId = recommendationEngine.getFirstItemId("ipad");
        assertEquals(Long.valueOf(955898738), actualId);
    }

    @Test
    public void testGetRecommendedProducts() throws Exception {

        String sampleRelatedProductResponse = readFile("sample-related-product-response.json");
        when(mockUtils.getAPIURL("nbp", "123"))
            .thenReturn("http://someurl");
        when(mockUtils.getJSONResponse("http://someurl"))
            .thenReturn(sampleRelatedProductResponse);

        List<Product> products = recommendationEngine.getRecommendedProducts(123l);
        assertEquals(6, products.size());

        Product product = products.get(0);
        assertEquals(Long.valueOf(52539554), product.getItemId());
        assertEquals("WWE 2K17, 2K, Xbox One, 710425497513", product.getName());

        product = products.get(1);
        assertEquals(Long.valueOf(51747226), product.getItemId());
        assertEquals("Call of Duty: Infinite Warfare, Activision, Xbox One, 047875878617", product.getName());

        product = products.get(2);
        assertEquals(Long.valueOf(51747224), product.getItemId());
        assertEquals("Call of Duty: Infinite Warfare Legacy Edition, Activision, Xbox One, 047875878631", product.getName());

        product = products.get(3);
        assertEquals(Long.valueOf(48982006), product.getItemId());
        assertEquals("Tom Clancy's Rainbow Six: Siege, Ubisoft, Xbox One, 887256014681", product.getName());

        product = products.get(4);
        assertEquals(Long.valueOf(52504864), product.getItemId());
        assertEquals("FIFA 17, Electronic Arts, Xbox One, 014633368727", product.getName());

        product = products.get(5);
        assertEquals(Long.valueOf(39099537), product.getItemId());
        assertEquals("Terraria, 505 Games, XBOX One, 812872018317", product.getName());
    }


}
