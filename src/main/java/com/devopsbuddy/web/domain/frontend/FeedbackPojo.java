package com.devopsbuddy.web.domain.frontend;


import java.io.Serializable;

public class FeedbackPojo implements Serializable {

    public static final long serialVersionUID = 1L;

    private String email;
    private String firstName;
    private String feedback;
    private String lastName;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "FeedbackPojo{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", feedback='" + feedback + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
