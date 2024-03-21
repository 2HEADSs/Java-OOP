package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.naming.OperationNotSupportedException;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {


    private static final Person PERSON1 = Mockito.mock(Person.class);
    private static final Person PERSON2 = Mockito.mock(Person.class);
    private static final Person PERSON3 = Mockito.mock(Person.class);
    //    private static final Person person4 = Mockito.mock(Person.class);
    private static final Person[] EXPECTED_PEOPLE = {PERSON1, PERSON2, PERSON3};
    private static final Integer EXPECTED_SIZE = EXPECTED_PEOPLE.length;
    private Database database;


    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(EXPECTED_PEOPLE);
    }

    @Test
    public void test_Constructor_Should_Create_CorrectObject() {
        Person[] actualPeople = database.getElements();
        Integer actualSize = actualPeople.length;
        //MESSAGE IS WHEN THE TEST FAILED
        assertArrayEquals("Arrays are not the same", EXPECTED_PEOPLE, actualPeople);
        assertEquals("Element count is incorrect", EXPECTED_SIZE, actualSize);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void test_ShouldThrow_WhenElementsAreGreater_Than16() throws OperationNotSupportedException {
        Person[] elements = new Person[17];
        database = new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_ShouldThrow_WhenElementsLess_Than0() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_ShouldAdd_Element_OnTheLastPosition() throws OperationNotSupportedException {
        Person mockPerson = Mockito.mock(Person.class);
        Mockito.when(mockPerson.getUsername()).thenReturn("Pavel");
        database.add(mockPerson);
        Person[] people = database.getElements();
        int actualSize = people.length;

        assertEquals(EXPECTED_SIZE + 1, actualSize);
        assertEquals(mockPerson.getUsername(), people[actualSize-1].getUsername());
        assertEquals(mockPerson, people[actualSize-1]);

    }
}