package com.github.atomic.utils;

public interface Constants {

    String DATE_FORMAT = "dd MMM, yyyy";
    String DATE_FULL_FORMAT = "dd MMM, yyyy hh:mm:ss a";
    String DATE_SQL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DATE_PATTERN_ISO8601 = "yyyy-MM-dd";
    String DATE_TIME_PATTERN_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss";
    String DATE_TIME_PATTERN_ISO8601_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ssZ";

    String TABLE_USER = "user";
    String TABLE_POST = "post";
    String TABLE_PERMISSION = "permission";
    String TABLE_ROLE = "role";
    String TABLE_STATUS = "status";
    String TABLE_USER_PERMISSION = "user_permission";

    int STATUS_DELETED = -100;

    String ROLE_ADMIN = "ROLE_ADMIN";
    String ROLE_USER = "ROLE_USER";


}
