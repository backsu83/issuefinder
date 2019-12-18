package com.issuefinder.crawling.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    public static final String DEFAULT_RESOURCE_NAME = "stock";

    private String id;
    private String resourceName;

    public ResourceNotFoundException(String id) {
        super("id : " + id + " does not exist.");
        this.id = id;
        this.resourceName = DEFAULT_RESOURCE_NAME;
    }

    public ResourceNotFoundException(String resourceName, String id) {
        super(resourceName + " : " + id + " does not exist.");
        this.id = id;
        this.resourceName = resourceName;
    }

}
