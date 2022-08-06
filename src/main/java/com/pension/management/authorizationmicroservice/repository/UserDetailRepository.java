package com.pension.management.authorizationmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pension.management.authorizationmicroservice.model.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, String>{

}
