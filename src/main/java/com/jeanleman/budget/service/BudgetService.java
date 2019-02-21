package com.jeanleman.budget.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jeanleman.budget.util.DataStore;

@Component
public class BudgetService {

	public BigDecimal calculateBananaCost(String startDate, int numOfDays) throws ParseException {

		if (!startDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
			throw new IllegalArgumentException();
		}
		BigDecimal totalCost = new BigDecimal("0.00");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date = df.parse(startDate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		Map<BigDecimal, List<Integer>> daysAndCosts = DataStore.getDaysAndCosts();
		for (int i = 1; i <= numOfDays; i++) {
			if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				for (BigDecimal amount : daysAndCosts.keySet()) {
					if (daysAndCosts.get(amount).contains(c.get(Calendar.DAY_OF_MONTH))) {
						totalCost = totalCost.add(amount).setScale(2, RoundingMode.DOWN);
					}
				}
			}
			c.add(Calendar.DATE, 1);
		}
		return totalCost;

	}

}
