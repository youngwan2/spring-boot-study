package com.baeksoo.shop.repository;

import com.baeksoo.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {}


