package com.app.UrlShortener.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Error {
    private String message;
    private ArrayList<String> messages = new ArrayList<>();

    public void addErrorMessage(String errorMessage) {
        this.messages.add(errorMessage);
    }

}
