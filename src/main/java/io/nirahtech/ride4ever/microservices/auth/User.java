package io.nirahtech.ride4ever.microservices.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class User implements Serializable {
    @Id
    private String username;
    private String password;
    @ManyToMany
    @JoinTable(name="USERS_ROLES")
    private Collection<Role> roles = new ArrayList<>();
    private boolean actived;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
    public boolean isActived() {
        return actived;
    }
    public void setActived(boolean actived) {
        this.actived = actived;
    }
}
