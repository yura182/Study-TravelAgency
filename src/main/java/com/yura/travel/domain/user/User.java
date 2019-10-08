package com.yura.travel.domain.user;

import java.util.Objects;

public class User {
    private static Long nextId = 1L;

    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String password;

    private User(UserBuilder userBuilder) {
        this.id = nextId++;
        this.name = userBuilder.name;
        this.surname = userBuilder.surname;
        this.email = userBuilder.email;
        this.phone = userBuilder.phone;
        this.password = userBuilder.password;
    }

    public User(User user, String password) {
        this.id = user.id;
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
        this.phone = user.phone;
        this.password = password;
    }

    public static UserBuilder init() {
        return new UserBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public static class UserBuilder {
        private String name;
        private String surname;
        private String email;
        private String phone;
        private String password;

        private UserBuilder() {

        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(Objects.toString(this.name +  " ", ""));
        out.append(Objects.toString(this.surname +  " ", ""));
        out.append(Objects.toString(this.email +  " ", ""));
        out.append(Objects.toString(this.phone +  " ", ""));
        return out.toString();
    }
}
