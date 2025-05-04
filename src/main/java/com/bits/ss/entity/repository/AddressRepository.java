package com.bits.ss.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bits.ss.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
