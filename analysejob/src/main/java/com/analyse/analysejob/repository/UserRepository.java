package com.analyse.analysejob.repository;

import com.analyse.analysejob.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u from User u where u.username = ?1 and u.password = ?2")
    User queryUserByUsernameAndPassword(String username, String password);

    @Query("select user from User user where user.username like %:username% or user.realname like %:realname% or user.profession like %:profession%")
    Page<User> getUsersByCondition(@Param("username") String username, @Param("realname") String realname, @Param("profession") String profession, Pageable pageable);

}