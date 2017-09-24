package com.devopsbuddy.backend.persistence.domain.backend;

import com.devopsbuddy.backend.persistence.converters.LocalDateTimeAttributeConverter;
import com.sun.javafx.beans.IDProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;
import sun.util.locale.provider.LocaleServiceProviderPool;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class PasswordResetToken implements Serializable{

    /** The serial version UID for Serializable classes */
    public static final long serialVersionUID = 1L;

    /**The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetToken.class);
    private static final int DEFAULT_TOKEN_LENGTH_IN_MINUTES = 120;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiry_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, User user, LocalDateTime creationDateTime, int expirationInMinutes) {
        if((null==token) || (user==null) || (null==creationDateTime)){
            throw new IllegalArgumentException("Null values are not accepted for token or user or creationdate");
        }

        if(expirationInMinutes==0) {
            LOG.warn("Assigning the token -default value, because input value is Zero");
            expirationInMinutes = DEFAULT_TOKEN_LENGTH_IN_MINUTES;
        }

        this.token = token;
        this.user = user;
        this.expiryDate = creationDateTime.plusMinutes(expirationInMinutes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordResetToken that = (PasswordResetToken) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
