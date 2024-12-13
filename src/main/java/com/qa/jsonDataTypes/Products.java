package com.qa.jsonDataTypes;

import java.util.Map;

public class Products {
    private Map<String, Product> details;

    public Map<String, Product> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Product> details) {
        this.details = details;
    }
}
