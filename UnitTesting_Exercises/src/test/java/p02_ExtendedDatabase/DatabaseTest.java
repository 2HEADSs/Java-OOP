package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {


    private static final Person PERSON1 = Mockito.mock(Person.class);
    private static final Person PERSON2 = Mockito.mock(Person.class);
    private static final Person PERSON3 = Mockito.mock(Person.class);
    //    private static final Person person4 = Mockito.mock(Person.class);
    private static final Person[] EXPECTED_PEOPLE = {PERSON1, PERSON2, PERSON3};
    private Database database;


    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(EXPECTED_PEOPLE);
    }

    @Test
    public void test_Constructor_Should_Create_CorrectObject() throws OperationNotSupportedException {
        Person[] actualPeople = database.getElements();
        Assert.assertArrayEquals(actualPeople, EXPECTED_PEOPLE);
    }


}