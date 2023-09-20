package com.example.security.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMassageType {
    USER_ALREADY_EXIST_SF("Пользователь %s уже существует"),
    USER_NOT_FOUND_SF("Пользователь %s не найден"),
    ROLE_NOT_FOUND_SF("Роль %s не найдена");
    private String text;

}
