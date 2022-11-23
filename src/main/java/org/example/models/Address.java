package org.example.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    @NotEmpty(message = "Street is required")
    @Size(min = 2, max = 50, message = "Street must be between 2 and 50 characters long")
    @Getter
    @Setter
    private String street;

    @NotEmpty(message = "City is required")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters long")
    @Getter
    @Setter
    private String city;

    @NotEmpty(message = "Zip code is required")
    @Getter
    @Setter
    private Integer zipCode;
}