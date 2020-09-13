package com.deval.congestionmicroservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

//TODO import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {

    private final UUID id;
    // TOOD: @NotBlank
    private final String name;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
