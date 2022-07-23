package com.wahyu.transaction.constant;

public enum TransactionConstant {

    PULSA("Beli Pulsa"),
    LISTRIK("Bayar Listrik"),
    SETOR("Setor Tunai"),
    TARIK("Tarik Tunai");

    public final String transaction;

    private TransactionConstant(String transaction) {
        this.transaction = transaction;
    }
}
