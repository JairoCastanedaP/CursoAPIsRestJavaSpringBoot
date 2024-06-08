package com.api.gestion.dao;

import com.api.gestion.pojo.User;
import com.api.gestion.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {

    User findByEmail(@Param(("email")) String email);

    List<UserWrapper> getAllUsers();

    List<String> getAllAdmins();

    
    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status,@Param("id") Integer id);
}
