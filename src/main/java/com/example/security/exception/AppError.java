package com.example.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppError {

    private int status;
    private String massage;
    private Date date;

    public AppError(int status, String massage) {
        this.status = status;
        this.massage = massage;
        this.date = new Date();
    }
}
