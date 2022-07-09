package com.example.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDto {
    private String name;
    private Boolean status;

    @Override
    public String toString() {
        return "StatusDto{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
