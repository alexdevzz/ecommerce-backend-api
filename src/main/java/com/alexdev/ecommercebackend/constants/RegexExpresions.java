package com.alexdev.ecommercebackend.constants;

import lombok.Getter;

import java.util.regex.Pattern;

public class RegexExpresions {

    public final static String rgxEmail = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

    public final static String rgxPhone = "^\\+\\d{5,15}$";

}
