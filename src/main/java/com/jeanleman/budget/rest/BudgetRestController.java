package com.jeanleman.budget.rest;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeanleman.budget.service.BudgetService;

@Controller
@RequestMapping("/budget")
public class BudgetRestController {

	@Autowired
	BudgetService budgetService;
	
	@PostMapping("/banana")
	@ResponseBody
	public String calculateBananaBudget(@RequestParam("startDate") String startDate,
			@RequestParam("numberOfDays") int numOfDays) {
		BigDecimal totalCost = new BigDecimal("0.00");
		try {
			totalCost = budgetService.calculateBananaCost(startDate, numOfDays);
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST.value()+" "+HttpStatus.BAD_REQUEST.getReasonPhrase()
			+" check the date format and make sure it's (MM/DD/YYYY)";
		}
		
		return "Total Cost: $"+totalCost;

	}

}
