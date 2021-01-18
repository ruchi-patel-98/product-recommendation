package com.ruchi.utils;


public class Utils {

//    public static List<RecommendedProduct> getProductListFromJSONArray(JSONArray jsonArray, int size) {
//        size = size<jsonArray.size()?size:jsonArray.size();
//        List<RecommendedProduct> productList = new ArrayList<RecommendedProduct>();
//        for (int cnt=0; cnt<size; cnt++) {
//            RecommendedProduct rp = new RecommendedProduct();
//            JSONObject jsonObject = (JSONObject) jsonArray.get(cnt);
//            rp.setItemId((Long)jsonObject.get("itemId"));
//            rp.setItemName((String)jsonObject.get("name"));
//
//            productList.add(rp);
//        }
//        return productList;
//    }
//
//    public static List<String> getReviewListFromJSONArray(JSONArray jsonArray) {
//        List<String> reviewList = new ArrayList<String>();
//        for (int cnt=0; cnt<jsonArray.size(); cnt++) {
//            JSONObject jsonObject = (JSONObject) jsonArray.get(cnt);
//            String review = (String)jsonObject.get("reviewText");
//            reviewList.add(review.toLowerCase());
//        }
//        return reviewList;
//    }
}
