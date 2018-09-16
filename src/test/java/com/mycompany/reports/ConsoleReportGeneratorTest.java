package com.mycompany.reports;

import com.mycompany.domain.Client;
import com.mycompany.domain.TradeDirection;
import com.mycompany.domain.TradeOrder;
import com.mycompany.utils.TradeUtils;
import org.javamoney.moneta.Money;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleReportGeneratorTest {

    @InjectMocks
    private ConsoleReportGenerator consoleReportGenerator = new ConsoleReportGenerator();

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private List<TradeOrder> tradeOrderList = new ArrayList<>();

    @Before
    public void setUpStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));


        Client c1 = new Client(1,"Jack");
        Client c2 = new Client(2,"Jill");
        Client c3 = new Client(3,"Bill");

        LocalDate d1 = LocalDate.of(2018,9,1);

        TradeOrder t1 = new TradeOrder("1", c1, TradeDirection.BUY,10,Money.of(10,"AED"),
                                        0.5, d1, d1, TradeUtils.calculateSettlementDate(d1,"AED"));

        TradeOrder t2 = new TradeOrder("2", c2, TradeDirection.BUY,10,Money.of(15,"SGD"),
                0.5, d1, d1, TradeUtils.calculateSettlementDate(d1,"SGD"));

        TradeOrder t3 = new TradeOrder("3", c2, TradeDirection.SELL,5,Money.of(20,"SGD"),
                0.3, d1, d1, TradeUtils.calculateSettlementDate(d1,"SGD"));

        TradeOrder t4 = new TradeOrder("4", c2, TradeDirection.SELL,5,Money.of(25,"SGD"),
                0.63, d1, d1, TradeUtils.calculateSettlementDate(d1,"SGD"));

        TradeOrder t5 = new TradeOrder("5", c3, TradeDirection.SELL,9,Money.of(25,"SGD"),
                0.61, d1, d1, TradeUtils.calculateSettlementDate(d1,"SGD"));

        tradeOrderList.add(t1);
        tradeOrderList.add(t2);
        tradeOrderList.add(t3);
        tradeOrderList.add(t4);
        tradeOrderList.add(t5);

    }

    @After
    public void revertStreams() {
        System.setOut(sysOut);
    }

    @Test
    public void testReportGeneration() {
        consoleReportGenerator.generateReport(tradeOrderList);

        assertThat(outContent.toString(), containsString("buySum = USD 125"));
        assertThat(outContent.toString(), containsString("sellSum = USD 246"));
    }
}
