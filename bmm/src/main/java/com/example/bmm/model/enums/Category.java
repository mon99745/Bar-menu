package com.example.bmm.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    Tang("탕"),
    Fry("볶음"),
    Unusual("별미"),
    Salad("샐러드"),
    Beverage("음료"),
    Alcohol("주류");

    public static final String ENUM = "";
    public static final String DEFAULT = "Tang";

    private final String value;
}
