package com.tveritin.client.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Setter
public class UserDto {
    private UUID id;
    private String username;
    private String name;
    private String surName;
    private String email;
    private String password;
    private Double balance;
    private UUID portfolioId;

    public UserDto() {
    }

    public UserDto id(UUID id) {
        this.id = id;
        return this;
    }

    @JsonProperty("id")
    public @Valid UUID getId() {
        return this.id;
    }

    public UserDto username(String username) {
        this.username = username;
        return this;
    }

    @JsonProperty("username")
    public String getUsername() {
        return this.username;
    }

    public UserDto name(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return this.name;
    }

    public UserDto surName(String surName) {
        this.surName = surName;
        return this;
    }

    @JsonProperty("SurName")
    public String getSurName() {
        return this.surName;
    }

    public UserDto email(String email) {
        this.email = email;
        return this;
    }

    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    public UserDto password(String password) {
        this.password = password;
        return this;
    }

    @JsonProperty("password")
    public String getPassword() {
        return this.password;
    }

    public UserDto balance(Double balance) {
        this.balance = balance;
        return this;
    }

    @JsonProperty("balance")
    public Double getBalance() {
        return this.balance;
    }

    public UserDto portfolioId(UUID portfolioId) {
        this.portfolioId = portfolioId;
        return this;
    }

    @JsonProperty("portfolioId")
    public @Valid UUID getPortfolioId() {
        return this.portfolioId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            UserDto userDto = (UserDto)o;
            return Objects.equals(this.id, userDto.id) && Objects.equals(this.username, userDto.username) && Objects.equals(this.name, userDto.name) && Objects.equals(this.surName, userDto.surName) && Objects.equals(this.email, userDto.email) && Objects.equals(this.password, userDto.password) && Objects.equals(this.balance, userDto.balance) && Objects.equals(this.portfolioId, userDto.portfolioId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.username, this.name, this.surName, this.email, this.password, this.balance, this.portfolioId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDto {\n");
        sb.append("    id: ").append(this.toIndentedString(this.id)).append("\n");
        sb.append("    username: ").append(this.toIndentedString(this.username)).append("\n");
        sb.append("    name: ").append(this.toIndentedString(this.name)).append("\n");
        sb.append("    surName: ").append(this.toIndentedString(this.surName)).append("\n");
        sb.append("    email: ").append(this.toIndentedString(this.email)).append("\n");
        sb.append("    password: ").append(this.toIndentedString(this.password)).append("\n");
        sb.append("    balance: ").append(this.toIndentedString(this.balance)).append("\n");
        sb.append("    portfolioId: ").append(this.toIndentedString(this.portfolioId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
