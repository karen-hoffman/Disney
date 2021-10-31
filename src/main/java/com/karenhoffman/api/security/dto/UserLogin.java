package com.karenhoffman.api.security.dto;

import javax.validation.constraints.NotBlank;

public class UserLogin {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String nombreUsuario) {
        this.username = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
