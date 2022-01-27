package com.example.task5.repository;

import com.example.task5.entities.User;
import liquibase.pro.packaged.Q;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM task_users", nativeQuery = true)
    List<User> getAll();

    @Modifying
    @Query(value = "INSERT INTO task_users (name, password, mail, login_date, status) VALUES (:name, :password, :mail, now(), :status)", nativeQuery = true)
    void createUser(@Param(value = "name") String name,
                    @Param(value = "password") String password,
                    @Param(value = "mail") String mail,
                    @Param(value = "status") Boolean status);

    @Query(value = "FROM User WHERE name = :name")
    @EntityGraph(attributePaths = {"roles"})
    User findUserByName(@Param(value = "name") String name);

    @Query(value = "SELECT id FROM task_users WHERE name=:name", nativeQuery = true)
    int findUserIdByName(@Param(value = "name") String name);

    @Modifying
    @Query(value = "DELETE FROM task_users WHERE name = :name", nativeQuery = true)
    void deleteUser(@Param(value = "name") String name);

    @Modifying
    @Query(value = "UPDATE task_users SET status = :status WHERE name = :name", nativeQuery = true)
    void changeStatusByName(@Param(value = "name") String name,
                             @Param(value = "status") Boolean status);
}
