package com.nt.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.CustomerPlan;
import com.nt.entity.Plan;
import com.nt.service.ICustomerPlanService;

@Controller
public class PlanController {
	@Autowired
	private ICustomerPlanService service;
	@GetMapping("/")
public String home(Model model) {
		model.addAttribute("plan", new Plan());
		model.addAttribute("status",service.getAllStatus());
		 model.addAttribute("plans",service.getAllPlan());
	return "index";
}//
	@PostMapping("/customers")
	public String custInfo(@ModelAttribute("plan") Plan plan ,RedirectAttributes model) {
		System.out.println("PlanController.custInfo()"+plan);
		List<CustomerPlan> customerByExample = service.getCustomerByExample(plan);
		System.out.println(customerByExample);
		model.addFlashAttribute("customers",customerByExample);
		
		return"redirect:./";
	}//
	@GetMapping("/excel")
	public void downloadExcel(HttpServletResponse res)throws Exception {
		res.setContentType("application/octet-stream");
		

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=records.xls";
		res.setHeader(headerKey, headerValue);
	        service.downloadExcel(res);
		
	}//excel
	@GetMapping("/pdfc")
	public void downlaodPdf(HttpServletResponse res) throws Exception {
		 res.setContentType("application/pdf");
		 String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=data.pdf";
	        res.setHeader(headerKey, headerValue);
	        service.downloadpdf(res);
	}//pdf
}
