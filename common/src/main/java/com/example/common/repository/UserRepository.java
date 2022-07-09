package com.example.common.repository;

import com.example.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM task_users", nativeQuery = true)
    List<User> getAll();

    @Modifying
    @Query(value = "INSERT INTO task_users (name, password, mail, login_date, status, hpassword) VALUES (:name, :password, :mail, now(), :status, :hpassword)", nativeQuery = true)
    void createUser(@Param(value = "name") String name,
                    @Param(value = "password") String password,
                    @Param(value = "mail") String mail,
                    @Param(value = "status") Boolean status,
                    @Param(value = "hpassword") String shaPassword);

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

    @Query(value = "Select * from user as u " +
            "join user_role as ur on ur.user_id=u.id" +
            "where u.name=:name and ur.role_id=:roleId", nativeQuery = true)
    Optional<User> findByNameAndRoleId(@Param("name") String name,
                                       @Param("roleId") int roleId);
}
