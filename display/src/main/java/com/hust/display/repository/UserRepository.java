package com.hust.display.repository;

import com.hust.display.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select t from User t where t.userName = :userName")
    User getUserByName(@Param("userName") String userName);

    @Modifying
    @Transactional
    @Query(value = "delete from user where user_name = ?1",nativeQuery = true)
    void deleteUser(String userName);
}
