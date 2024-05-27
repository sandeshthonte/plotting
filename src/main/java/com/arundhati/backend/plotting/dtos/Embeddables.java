package com.arundhati.backend.plotting.dtos;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
public class Embeddables {
    @Embeddable
    @Getter
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String postalCode;
    }
}