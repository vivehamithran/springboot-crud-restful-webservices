package com.aws.crud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.crud.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
