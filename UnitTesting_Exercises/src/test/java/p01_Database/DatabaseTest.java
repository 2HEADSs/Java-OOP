package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    private static final Integer[] EXPECTED_ELEMENTS = {1, 2, 3, 4, 5};
    private static final int EXPECTED_SIZE = EXPECTED_ELEMENTS.length;
    private static final int EXPECTED_INDEX = EXPECTED_ELEMENTS.length - 1;
    Integer[] EXPECTED_ELEMENTS_MORE_THAN_16 = {1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(EXPECTED_ELEMENTS);
    }

    @Test
    public void testConstructor_Should_Create_Correct_Object() throws OperationNotSupportedException {
        Integer[] actualElements = database.getElements();
        assertArrayEquals(EXPECTED_ELEMENTS, actualElements);
        assertEquals(EXPECTED_SIZE, actualElements.length);
        assertEquals(EXPECTED_INDEX, actualElements.length - 1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructor_Should_Throw_When_ElementsAreGreaterThan16() throws OperationNotSupportedException {
        this.database = new Database(EXPECTED_ELEMENTS_MORE_THAN_16);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructor_Should_Throw_When_ElementsAreZero() throws OperationNotSupportedException {
        this.database = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Should_Throw_When_ElementsIsNull() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void test_Should_Add_Integer_ElementOnTheLastPosition() throws OperationNotSupportedException {
        int lastAddedElement = 25;
        this.database.add(lastAddedElement);
        Integer[] elements = database.getElements();
        int actualSize = elements.length;

        assertEquals(EXPECTED_SIZE + 1, actualSize);
        assertEquals(Integer.valueOf(lastAddedElement), elements[actualSize - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Remove_ShouldThrow_When_DatabaseIsEmpty() throws OperationNotSupportedException {
        for (int i = 0; i <= EXPECTED_SIZE; i++) {
            database.remove();
        }
    }

    @Test
    public void test_Remove_Should_Resize_Length() throws OperationNotSupportedException {
        for (int i = 0; i < EXPECTED_SIZE; i++) {
            database.remove();
        }
        Assert.assertEquals(0, database.getElements().length);
    }


}