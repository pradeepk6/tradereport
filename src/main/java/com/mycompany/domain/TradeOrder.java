package com.mycompany.domain;

import org.javamoney.moneta.Money;
import javax.money.MonetaryAmount;
import java.time.LocalDate;

/**
 * TODO
 */
public class TradeOrder {

    protected String id;
    protected TradeDirection tradeDirection;
    protected long units;
    protected Money pricePerUnit;
    protected double agreedFX;

    protected LocalDate instructionDate;
    protected LocalDate instructionSettlementDate;
    protected LocalDate appliedSettlementDate;
    protected Client client;

    public TradeOrder(String id, Client client, TradeDirection tradeDirection, long units, Money pricePerUnit, double agreedFX,
                      LocalDate instructionLocalDate, LocalDate instructionSettlementLocalDate, LocalDate appliedSettlementLocalDate) {
        this.id = id;
        this.client = client;
        this.tradeDirection = tradeDirection;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.agreedFX = agreedFX;
        this.instructionDate = instructionLocalDate;
        this.instructionSettlementDate = instructionSettlementLocalDate;
        this.appliedSettlementDate = appliedSettlementLocalDate;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public Money getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Money pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getAgreedFX() {
        return agreedFX;
    }

    public void setAgreedFX(Double agreedFX) {
        this.agreedFX = agreedFX;
    }

    public MonetaryAmount getTotalTradeAmt() {
        return Money.of(pricePerUnit.multiply(units).multiply(agreedFX).getNumberStripped(), "USD");
    }

    public TradeDirection getTradeDirection() {
        return tradeDirection;
    }

    public void setTradeDirection(TradeDirection tradeDirection) {
        this.tradeDirection = tradeDirection;
    }

    public long getUnits() {
        return units;
    }

    public void setUnits(long units) {
        this.units = units;
    }

    public LocalDate getInstructionLocalDate() {
        return instructionDate;
    }

    public void setInstructionLocalDate(LocalDate instructionLocalDate) {
        this.instructionDate = instructionLocalDate;
    }

    public LocalDate getInstructionSettlementLocalDate() {
        return instructionSettlementDate;
    }

    public void setInstructionSettlementLocalDate(LocalDate instructionSettlementLocalDate) {
        this.instructionSettlementDate = instructionSettlementLocalDate;
    }

    public LocalDate getAppliedSettlementLocalDate() {
        return appliedSettlementDate;
    }

    private void setAppliedSettlementLocalDate(LocalDate appliedSettlementLocalDate) {
        this.appliedSettlementDate = appliedSettlementLocalDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
