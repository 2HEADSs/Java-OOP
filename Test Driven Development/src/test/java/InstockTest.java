import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class InstockTest {
    private static final String exercise = "sss";
    private static final String LABEL = "test_label";
    private static final double PRICE = 15.00;
    private static final int QUANTITY = 10;
    private static final double DUPLICATED_PRICE = 6.99;

    private static final double BEGIN = 20.01;
    private static final double END = 23.99;

    private Instock instock;
    private Product product;

    @Before
    public void setUp() {
        this.instock = new Instock();
        this.product = new Product(LABEL, PRICE, QUANTITY);
    }

    @Test
    public void test_Contains_Returns_Correct_Result() {
        instock.add(product);
        assertTrue(instock.contains(product));
        assertFalse(instock.contains(new Product("not_added", PRICE, QUANTITY)));
    }

    @Test
    public void test_Count_Returns_Correct_Result() {
        assertEquals(0, instock.getCount());
        instock.add(product);
        assertEquals(1, instock.getCount());
    }

    @Test
    public void test_ChangeQuantity_AppliesNewQuantity_When_ProductPresent() {
        instock.add(product);
        int expectedQuantity = QUANTITY * 7;
        instock.changeQuantity(product.getLabel(), expectedQuantity);
        assertEquals(expectedQuantity, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ChangeQuantity_Throws_When_ProductNotPresent() {
        instock.changeQuantity(product.getLabel(), QUANTITY);
    }

    @Test
    public void test_FindByIndex_ShouldReturn_ProductInInsertionOrder() {
        List<Product> products = addProducts();
        int index = 3;
        String expectedLabel = products.get(index).getLabel();
        Product product = instock.find(index);
        assertNotNull(product);
        String actualLabel = product.getLabel();
        assertEquals(expectedLabel, actualLabel);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_FindByIndex_ShouldThrown_When_IndexOutOfBones() {
        instock.find(instock.getCount() + 1);
    }

    @Test
    public void test_FindByLabel_ShouldReturn_ProductWithMatchingLabel() {
        instock.add(product);
        Product foundProduct = instock.findByLabel(product.getLabel());
        assertNotNull(foundProduct);
        assertEquals(product.getLabel(), foundProduct.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FindByLabel_ShouldThrown_WhenLabelIsMissing() {
        instock.findByLabel(product.getLabel());

    }


    @Test
    public void test_findFirstByAlphabeticalOrder_ShouldReturn_CorrectCountOfProducts() {
        int count = addProducts().size() - 2;
        List<Product> products = toList(instock.findFirstByAlphabeticalOrder(count));
        assertEquals(count, products.size());
    }

    @Test
    public void test_findFirstByAlphabeticalOrder_ShouldReturn_ProductsOrderedByLabel() {
        List<Product> expectedProducts = addProducts().stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());
        List<Product> actualSortedProducts = toList(instock.findFirstByAlphabeticalOrder(expectedProducts.size()));
        assertProductListEqual(expectedProducts, actualSortedProducts);
    }

    @Test
    public void test_findFirstByAlphabeticalOrder_ShouldReturn_EmptySet_When_CountIsTooLarge() {
        List<Product> products = toList(instock.findFirstByAlphabeticalOrder(1));
        assertTrue(products.isEmpty());
    }

    @Test
    public void test_FindAllInRange_ShouldReturn_Correct_Range() {
        addProducts();
        List<Product> products = toList(instock.findAllInRange(BEGIN, END));
        products
//                .stream().
//                .mapToDouble(Product::getPrice)
//                .forEach(p -> assertTrue(p > begin && p <= end));
                .forEach(p -> assertTrue(p.getPrice() > BEGIN && p.getPrice() <= END));

    }

    @Test
    public void test_FindAllInRange_ShouldReturnIn_DescendingOrder() {

        List<Product> expectedProducts = addProducts()
                .stream()
                .filter(p -> p.getPrice() > BEGIN && p.getPrice() <= END)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
        List<Product> actualProducts = toList(instock.findAllInRange(BEGIN, END));
        assertProductListEqual(expectedProducts, actualProducts);
    }

    @Test
    public void test_FindAllInRange_ShouldReturn_EmptySet_When_NoMatches() {
        double maxPrice = addProducts().stream()
                .mapToDouble(Product::getPrice)
                .max()
                .getAsDouble();

        List<Product> products = toList(instock.findAllInRange(maxPrice, maxPrice + 1));
        assertTrue(products.isEmpty());
    }


    @Test
    public void test_FindAllByPrice_ShouldReturn_CorrectProduct_Whit_CurrentPrice() {
        addProducts();
        List<Product> products = toList(instock.findAllByPrice(DUPLICATED_PRICE));
        products.forEach(p -> assertEquals(DUPLICATED_PRICE, p.getPrice(), 0.00));
    }

    @Test
    public void test_FindAllByPrice_ShouldReturn_EmptySet_When_NoMatches() {
        addProducts();
        List<Product> products = toList(instock.findAllByPrice(100));
        assertTrue(products.isEmpty());
    }

    @Test
    public void test_FindFirstMostExpensiveProducts_ShouldReturn_CorrectCountOfProducts() {
        int count = 3;
        addProducts();
        List<Product> products = toList(instock.findFirstMostExpensiveProducts(count));
        assertEquals(3, products.size());
    }

    @Test
    public void test_FindFirstMostExpensiveProducts_ShouldReturn_MostExpensiveProducts() {
        int count = 3;
        List<Product> expectedProducts = addProducts().stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
        List<Product> actualProducts = toList(instock.findFirstMostExpensiveProducts(count));
        assertEquals(expectedProducts.size(), actualProducts.size());
        assertProductListEqual(expectedProducts, actualProducts);
    }

    @Test
    public void test_FindFirstMostExpensiveProducts_ShouldReturn_EmptySet_IfCountIsLargerThanSize() {
        int size = addProducts().size();
        List<Product> products = toList(instock.findFirstMostExpensiveProducts(size + 1));
        assertTrue(products.isEmpty());
    }

    @Test
    public void test_Iterator_ReturnsAllProducts() {
        List<Product> expectedProducts = addProducts();
        Iterator<Product> iterator = instock.iterator();
        assertNotNull(iterator);
        Iterable<Product> iterable = () -> iterator;
        List<Product> actualProducts = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        assertProductListEqual(expectedProducts,actualProducts);
    }

    private List<Product> addProducts() {
        List<Product> products = List.of(
                new Product("test_label_1", 13.99, QUANTITY),
                new Product("test_label_3", 21.99, 50),
                new Product("test_label_2", BEGIN, 23),
                new Product("test_label_6", 1.99, QUANTITY),
                new Product("test_label_5", DUPLICATED_PRICE, 1),
                new Product("test_label_4", DUPLICATED_PRICE, 2),
                new Product("test_label_7", END, 99)
        );
        products.forEach(instock::add);
        return products;
    }

    private List<Product> toList(Iterable<Product> iterable) {
        assertNotNull(iterable);
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    private static void assertProductListEqual(List<Product> expectedProducts, List<Product> actualSortedProducts) {
        assertEquals(expectedProducts, actualSortedProducts);
        for (int i = 0; i < expectedProducts.size(); i++) {
            String expectedLabel = expectedProducts.get(i).getLabel();
            String actualLabel = actualSortedProducts.get(i).getLabel();
            assertEquals(expectedLabel, actualLabel);
        }
    }
}
