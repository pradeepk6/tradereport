package com.mycompany.reports;

import com.mycompany.domain.TradeOrder;

import java.util.List;

public interface Report {
    void generateReport(List<TradeOrder> list);
}
