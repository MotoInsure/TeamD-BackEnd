package com.ibm.motoInsure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.motoInsure.entity.Policy;


public interface PolicyRepository extends JpaRepository<Policy,Integer> {



}
