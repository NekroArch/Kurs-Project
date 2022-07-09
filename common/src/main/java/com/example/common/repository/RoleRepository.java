package com.example.common.repository;

import com.example.entities.Role;
import com.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Modifying
    @Query(value = "INSERT INTO user_role (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void setRole(
            @Param(value = "userId") int userId,
            @Param(value = "roleId") int roleId
    );

    @Modifying
    @Query(value = "UPDATE user_role SET role_id = :roleId WHERE user_id = :userId", nativeQuery = true)
    void changeStatus(@Param(value = "userId") int userId,
                            @Param(value = "roleId") int roleId);

}
