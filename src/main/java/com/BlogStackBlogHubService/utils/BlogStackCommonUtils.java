package com.BlogStackBlogHubService.utils;

import com.BlogStackBlogHubService.commons.BlogStackCommonConstants;

import java.math.BigInteger;
import java.util.UUID;

public enum BlogStackCommonUtils {

    INSTANCE;

    public String uniqueIdentifier(String... prefix) {
        return (prefix.length > BigInteger.ZERO.intValue() ? prefix[BigInteger.ZERO.intValue()] : BlogStackCommonConstants.INSTANCE.BLANK_STRING).concat(UUID.randomUUID().toString().replace(BlogStackCommonConstants.INSTANCE.HYPHEN_STRING, BlogStackCommonConstants.INSTANCE.BLANK_STRING));
    }

    public String getMessageStringFromException(String exceptionMessage) {
        return exceptionMessage.substring(BigInteger.ZERO.intValue(), exceptionMessage.lastIndexOf(BlogStackCommonConstants.INSTANCE.SERVICE_STRING) - BigInteger.ONE.intValue()).strip();
    }
}
