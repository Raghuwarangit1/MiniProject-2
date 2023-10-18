package com.nt.repositories;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.nt.entity.CustomerPlan;


public interface CustomerRepository extends JpaRepository<CustomerPlan,Integer> {
	@Query("SELECT DISTINCT(pName) FROM CustomerPlan")
	@Modifying
	public List<String>getAllPlans();
	@Query("SELECT DISTINCT(status) FROM CustomerPlan")
	@Modifying
	public List<String>getAllStatus();
	
	

}
