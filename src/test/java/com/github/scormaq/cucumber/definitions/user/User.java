package com.github.scormaq.cucumber.definitions.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Boolean isAdmin;
    private Boolean isNotAdmin;
    private String gradpaName;
    private Boolean isNotPassive;
    private String username;
    private String password;
}
