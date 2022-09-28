package com.example.ljudio.enums;

public enum Role {
    ADMIN(1),
    User(2);

    final int value;

    Role(int value) {
        this.value = value;
    }
}
