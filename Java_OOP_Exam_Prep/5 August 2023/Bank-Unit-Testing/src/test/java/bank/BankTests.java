package bank;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTests {
    private Bank bank;
    private Client client;

    @Before
    public void setUp() {
        this.bank = new Bank("Allianz", 3);
        client = new Client("Pavel");
        bank.addClient(client);

    }

    @Test(expected = NullPointerException.class)
    public void test_Set_Name_Should_Throw() {
    Bank bank1 = new Bank("",3);
    }

    @Test
    public void test_Set_Name() {
        Bank bank1 = new Bank("test",3);
        Assert.assertEquals("test", bank1.getName());

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Capacity_Should_Throw() {
        Bank bank1 = new Bank("test",-21);
    }

    @Test
    public void test_Get_Name() {
        Assert.assertEquals("Allianz", bank.getName());
    }


    @Test
    public void test_Set_Capacity() {
        Bank bank2 = new Bank("test",2);
        Assert.assertEquals(2, bank2.getCapacity());
    }

    @Test
    public void test_Get_Capacity() {
        Assert.assertEquals(3, bank.getCapacity());
    }

    @Test
    public void test_Count() {
        Assert.assertEquals(1, bank.getCount());
    }

    @Test
    public void test_AddClient() {
        Client client2 = new Client("Stefan");
        bank.addClient(client2);
        Assert.assertEquals(2, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddClient_In_Full_Bank_Should_Throw() {
        Client client2 = new Client("Stefan");
        Client client3 = new Client("Stefan2");
        Client client4 = new Client("Stefan3");
        bank.addClient(client2);
        bank.addClient(client3);
        bank.addClient(client4);
        Assert.assertEquals(2, bank.getCount());

    }

    @Test
    public void test_Remove_Client() {
        bank.removeClient("Pavel");
        Assert.assertEquals(0, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_Client_Should_Throw() throws IllegalArgumentException{
        bank.removeClient("s");
    }

    @Test
    public void test_LoanWithdrawal() {
        bank.loanWithdrawal("Pavel");
        Assert.assertFalse(client.isApprovedForLoan());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_LoanWithdrawal_Should_Throw() throws IllegalArgumentException{
        bank.removeClient("Pavel");
        Assert.assertEquals(client, bank.loanWithdrawal("Pavel"));
    }

    @Test
    public void test_Statistic() {
        Assert.assertEquals("The client Pavel is at the Allianz bank!", bank.statistics());
    }

}
