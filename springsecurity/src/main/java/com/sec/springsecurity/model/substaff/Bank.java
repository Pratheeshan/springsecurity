package com.sec.springsecurity.model.substaff;

import jakarta.validation.constraints.NotNull;

public class Bank {
    @NotNull(message = "Name in Account is mandatory")
    private String nameInAccount;

    @NotNull(message = "Bank is mandatory")
    private String bank;

    @NotNull(message = "Account Number is mandatory")
    private Integer accountNumber;

    @NotNull(message = "Bank Branch is mandatory")
    private String bankBranch;

    public Bank(String nameInAccount, @NotNull String bank, @NotNull Integer accountNumber, @NotNull String bankBranch) {
        this.nameInAccount = nameInAccount;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.bankBranch = bankBranch;
    }


    public String getNameInAccount() {
        return nameInAccount;
    }

    @NotNull
    public String getBank() {
        return bank;
    }

    @NotNull
    public Integer getAccountNumber() {
        return accountNumber;
    }

    @NotNull
    public String getBankBranch() {
        return bankBranch;
    }

    @Override
    public String toString() {
        return "Bank{" +
                ", name_in_account='" + nameInAccount + '\'' +
                ", bank='" + bank + '\'' +
                ", account_number=" + accountNumber +
                ", bank_branch='" + bankBranch + '\'' +
                '}';
    }
}
