package com.qa.utils;

import com.qa.jsonDataTypes.Products;

import static com.qa.utils.JsonUtils.convert;
import static java.util.Objects.isNull;

public class JsonTestFileUtils {


    private static Products products;

    public static Products getProducts() {
        if (isNull(products)) {
            products = convert("products.json", Products.class);
        }
        return products;
    }
}
