package com.mycompany.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * TODO
 */
public class TradeUtils {

    /**
     * TODO
     */
    public  static LocalDate calculateSettlementDate(LocalDate instructionDate, String currencyString) {
        LocalDate appliedSettlementDate = instructionDate;
        DayOfWeek day = instructionDate.getDayOfWeek();
        if (currencyString.equalsIgnoreCase("AED") || currencyString.equalsIgnoreCase("SAR")) {
            if (day == DayOfWeek.FRIDAY) {
                appliedSettlementDate = instructionDate.plusDays(2);
            } else if (day == DayOfWeek.SATURDAY) {
                appliedSettlementDate = instructionDate.plusDays(1);
            }
        }else {
            if (day == DayOfWeek.SATURDAY) {
                appliedSettlementDate = instructionDate.plusDays(2);
            } else if (day == DayOfWeek.SUNDAY) {
                appliedSettlementDate = instructionDate.plusDays(1);
            }
        }
        return appliedSettlementDate;
    }
}
