package bank.core;

import bank.common.ConstantMessages;
import bank.common.ExceptionMessages;
import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private LoanRepository loans;
    private Collection<Bank> banks;

    public ControllerImpl() {
        this.loans = new LoanRepository();
        this.banks = new ArrayList<>();
    }

    @Override
    public String addBank(String type, String bankName) {
        Bank bank;
        if (type.equals("CentralBank")) {
            bank = new CentralBank(bankName);
        } else if (type.equals("BranchBank")) {
            bank = new BranchBank(bankName);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_BANK_TYPE);

        }
        this.banks.add(bank);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String addLoan(String type) {
        Loan loan;
        if (type.equals("StudentLoan")) {
            loan = new StudentLoan();
        } else if (type.equals("MortgageLoan")) {
            loan = new MortgageLoan();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_LOAN_TYPE);

        }
        this.loans.addLoan(loan);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Loan loan = this.loans.findFirst(loanType);
        if (loan == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND, loanType));
        }
        for (Bank bank : this.banks) {
            if (bank.getName().equals(bankName)) {
                bank.addLoan(loan);
                this.loans.removeLoan(loan);
            }
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        Client client;
        if (clientType.equals("Student")) {
            client = new Student(clientName, clientID, income);
        } else if (clientType.equals("Adult")) {
            client = new Adult(clientName, clientID, income);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CLIENT_TYPE);
        }
        for (Bank bank : this.banks) {
            if (bank.getName().equals(bankName)) {
                if (clientType.equals("Adult") && bank.getClass().getSimpleName().equals("CentralBank")) {
                    bank.addClient(client);
                    return String.format(
                            ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);

                } else if (clientType.equals("Student") && (bank.getClass().getSimpleName().equals("BranchBank"))) {
                    bank.addClient(client);
                    return String.format(
                            ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);

                } else {
                    return ConstantMessages.UNSUITABLE_BANK;
                }
            }
        }
        return null;
    }

    @Override
    public String finalCalculation(String bankName) {
        double funds = 0;
        for (Bank bank : this.banks) {
            if(bank.getName().equals(bankName)){
                for (Client client : bank.getClients()) {
                    funds += client.getIncome();
                }
                for (Loan loan : bank.getLoans()) {
                    funds +=loan.getAmount();
                }
            }
        }
        return String.format(ConstantMessages.FUNDS_BANK,bankName,funds);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        for (Bank bank : this.banks) {
            sb.append(bank.getStatistics());
        }
        return sb.toString().trim();
    }
}
