package com.jeanleman.budget.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {

	private static Map<BigDecimal, List<Integer>> daysAndCosts = new HashMap<>();

	public static Map<BigDecimal, List<Integer>> getDaysAndCosts() {
		BigDecimal cost = new BigDecimal(0.00).setScale(2);
		BigDecimal increm = new BigDecimal(0.05);
		int dayCount = 0;
		for (int i = 0; i < 5; i++) {
			cost = cost.add(increm);
			cost = cost.setScale(2, RoundingMode.DOWN);
			List<Integer> days = new ArrayList<>();
			for (int k = 1; k <= 7; k++) {
				dayCount++;
				if (dayCount > 31) {
					break;
				}
				days.add(dayCount);
			}
			daysAndCosts.put(cost, days);
		}

		return daysAndCosts;

	}

}
