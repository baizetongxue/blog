package com.sc.enums;

/**
 * Created by BaiZe schoolmate on 2020/5/10 21:34.
 */
public enum SqlEnum {
    SUCCESS(1),FAILURE(0);
    private int i;

    SqlEnum(int i) {
        this.i =i;
    }
}
