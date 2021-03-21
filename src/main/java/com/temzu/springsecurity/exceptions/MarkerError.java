package com.temzu.springsecurity.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MarkerError {
    private int status;
    private String message;
    private Date timestamp;

    public MarkerError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

}
