package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private int roleId;
}
