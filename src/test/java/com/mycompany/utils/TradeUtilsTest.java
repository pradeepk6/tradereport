package com.mycompany.utils;

import com.mycompany.domain.Client;
import com.mycompany.domain.TradeDirection;
import com.mycompany.domain.TradeOrder;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import static junit.framework.TestCase.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class TradeUtilsTest {

    LocalDate d1 = LocalDate.of(2018,9,1);
    LocalDate d2 = LocalDate.of(2018,9,3);

    @Before
    public void setUp() {
    }

    @Test
    public void testCalculateSettlementDate() {
        LocalDate appliedDate = TradeUtils.calculateSettlementDate(d1,"AED");
        assertTrue(appliedDate.equals(LocalDate.of(2018,9,2)));

        appliedDate = TradeUtils.calculateSettlementDate(d1,"USD");
        assertTrue(appliedDate.equals(LocalDate.of(2018,9,3)));

        appliedDate = TradeUtils.calculateSettlementDate(d2,"USD");
        assertTrue(appliedDate.equals(LocalDate.of(2018,9,3)));
    }
}
