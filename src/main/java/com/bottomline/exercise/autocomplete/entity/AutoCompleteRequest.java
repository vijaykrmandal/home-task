package com.bottomline.exercise.autocomplete.entity;

import jakarta.validation.constraints.NotBlank;

public class AutoCompleteRequest {

    @NotBlank(message = "Prefix must not be blank")
    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
