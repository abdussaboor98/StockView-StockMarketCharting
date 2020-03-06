package com.cts.training.stockview.stockpriceservice.model;

import java.time.LocalDate;
import java.util.List;

public class ImportSummary {

	int numberOfRecordsInserted;
	LocalDate startDate;
	LocalDate endDate;
	List<String> duplicates;
}
