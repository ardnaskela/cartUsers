package com.cartdemo.user.repository;

import com.cartdemo.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndActiveIs(String email, boolean active);
    Optional<UserEntity> findById(Long id);
    void deleteById(Long id);
}
