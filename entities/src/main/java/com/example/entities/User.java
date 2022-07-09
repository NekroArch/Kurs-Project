package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_users")
public class User extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;

    @Column(name = "login_date")
    private Timestamp loginDate;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "hpassword")
    private String hpassword;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name="user_id") },
            inverseJoinColumns = {@JoinColumn(name="role_id")})
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Collection> collections;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", loginDate=" + loginDate +
                ", status=" + status +
                ", hpassword=" + hpassword +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(mail, user.mail) && Objects.equals(loginDate, user.loginDate) && Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, mail, loginDate, status);
    }
}
