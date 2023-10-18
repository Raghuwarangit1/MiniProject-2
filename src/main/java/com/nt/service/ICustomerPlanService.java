package com.nt.service;



import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.nt.entity.CustomerPlan;
import com.nt.entity.Plan;

public interface ICustomerPlanService {

	public String savePlan(CustomerPlan plan);


public List<String>getAllPlan();
public List<String>getAllStatus();


public List<CustomerPlan>getAllCustomers();
public List<CustomerPlan>getCustomerByExample(Plan plan);
public void downloadExcel(HttpServletResponse res)throws Exception;
public void downloadpdf(HttpServletResponse res)throws Exception;


}
