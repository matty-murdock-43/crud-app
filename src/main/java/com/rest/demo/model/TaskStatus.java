package com.rest.demo.model;


import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    TO_DO("To do"),
    IN_PROGRESS("In progress"),
    ON_HOLD("On hold"),
    COMPLETED("Completed");

    private String label;

    TaskStatus() {
    }

    TaskStatus(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }

    @JsonValue
    public String toValue(){
        return this.getLabel();
    }
}
