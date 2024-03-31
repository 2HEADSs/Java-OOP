package bank.entities.bank;

import bank.common.ExceptionMessages;
import bank.entities.client.Client;
import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseBank implements Bank {

    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;

    public BaseBank(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.loans = new ArrayList<>();
        this.clients = new ArrayList<>();

    }

    @Override
    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public Collection<Client> getClients() {
        return this.clients;
    }

    @Override
    public Collection<Loan> getLoans() {
        return this.loans;
    }

    @Override
    public void addClient(Client client) {
        if (this.clients.size() >= this.capacity) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_ENOUGH_CAPACITY_FOR_CLIENT);
        }
        this.clients.add(client);
    }

    @Override
    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    @Override
    public int sumOfInterestRates() {
        int sum = 0;
        for (Loan loan : this.loans) {
            sum += loan.getInterestRate();
        }
        return sum;
    }

    @Override
    public String getStatistics() {

        if (this.clients.isEmpty()) {
            return String.format("Name: %s, Type: %s%nClients: none%nLoans: %d,Sum of interest rates: %d%n",
                    this.name,
                    this.getClass().getSimpleName(),
                    this.loans.size(),
                    this.sumOfInterestRates()
            );
        } else {
            return String.format("Name: %s, Type: %s%nClients: %s%nLoans: %d,Sum of interest rates: %d%n",
                    this.name,
                    this.getClass().getSimpleName(),
                    this.clients.stream().map(Client::getName).collect(Collectors.joining(", ")),
                    this.loans.size(),
                    this.sumOfInterestRates()
            );
        }
    }
}
