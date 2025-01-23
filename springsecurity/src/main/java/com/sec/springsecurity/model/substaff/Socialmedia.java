package com.sec.springsecurity.model.substaff;

import jakarta.validation.constraints.NotNull;

public class Socialmedia {

    @NotNull(message = "Linkedin Account is mandatory")
    private String linkedin;
    private String github;
    private String facebook;

    public Socialmedia(@NotNull String linkedin, String github, String facebook) {
        this.linkedin = linkedin;
        this.github = github;
        this.facebook = facebook;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getGithub() {
        return github;
    }

    public String getFacebook() {
        return facebook;
    }

    @Override
    public String toString() {
        return "Socialmedia{" +
                "linkedin='" + linkedin + '\'' +
                ", github='" + github + '\'' +
                ", facebook='" + facebook + '\'' +
                '}';
    }
}
