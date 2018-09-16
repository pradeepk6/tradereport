package com.mycompany.reports;

import com.mycompany.domain.Client;
import com.mycompany.domain.TradeDirection;
import com.mycompany.domain.TradeOrder;
import com.mycompany.utils.TradeUtils;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.function.MonetaryFunctions;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * TODO
 */
public class ConsoleReportGenerator implements Report {

    public void generateReport(List<TradeOrder> tradeOrders) {

        Map<TradeDirection, Map<Client, Optional<MonetaryAmount>>> reportMap  = tradeOrders.stream().collect(groupingBy(TradeOrder::getTradeDirection,
                groupingBy(TradeOrder::getClient,mapping(TradeOrder::getTotalTradeAmt,reducing(MonetaryFunctions.sum())))
        ));

        Optional<MonetaryAmount> buySum = reportMap.get(TradeDirection.BUY).values().stream().filter(Optional::isPresent).map(Optional::get).reduce(MonetaryFunctions.sum());
        Optional<MonetaryAmount> sellSum = reportMap.get(TradeDirection.SELL).values().stream().filter(Optional::isPresent).map(Optional::get).reduce(MonetaryFunctions.sum());
        System.out.println("buySum = " + buySum.get());
        System.out.println("sellSum = " + sellSum.get());

        System.out.println("Ranked Buy :");
        reportMap.get(TradeDirection.BUY).entrySet().stream()
                .filter(e -> e.getValue().isPresent())
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().get()) )
                .sorted(Map.Entry.comparingByValue(MonetaryFunctions.sortNumberDesc()))
                .forEach(e -> System.out.println( ((Client)e.getKey()).getName() + " : " +e.getValue()));

        System.out.println("Ranked Sell :");
        reportMap.get(TradeDirection.SELL).entrySet().stream()
                .filter(e -> e.getValue().isPresent())
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().get()) )
                .sorted(Map.Entry.comparingByValue(MonetaryFunctions.sortNumberDesc()))
                .forEach(e -> System.out.println( ((Client)e.getKey()).getName() + " : " +e.getValue()));

    }

    /*
    public static void main(String args[]) {
        ConsoleReportGenerator consoleReportGenerator = new ConsoleReportGenerator();
        List<TradeOrder> tradeOrderList = new ArrayList<>();

        Client c1 = new Client(1,"Jack");
        Client c2 = new Client(2,"Jill");
        Client c3 = new Client(3,"Bill");

        LocalDate d1 = LocalDate.of(2018,9,1);

        TradeOrder t1 = new TradeOrder("1", c1, TradeDirection.BUY,10, Money.of(10,"AED"),
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
        consoleReportGenerator.generateReport(tradeOrderList);
    }
    */
}
