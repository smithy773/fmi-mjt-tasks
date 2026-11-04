package bg.sofia.uni.fmi.mjt.order.analyzer;

import bg.sofia.uni.fmi.mjt.order.domain.Category;
import bg.sofia.uni.fmi.mjt.order.domain.Order;
import bg.sofia.uni.fmi.mjt.order.loader.OrderLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrderAnalyzerImplTest {
    private static OrderAnalyzerImpl orderAnalyzerOnly2;
    private static OrderAnalyzerImpl orderAnalyzer100;
    private static List<Order> ordersCustom;
    private static Order order1;
    private static Order order2;

    @BeforeAll
    public static void setup() throws IOException {
        String filePathOnly2 = "src/test/resources/ONLY2amazon_sales_data_2025.csv";
        String filePath100 = "src/test/resources/amazon_sales_data_2025.csv";

        Reader readerOnly2 = new FileReader(filePathOnly2);
        Reader reader100 = new FileReader(filePath100);

        List<Order> ordersOnly2 = OrderLoader.load(readerOnly2);
        List<Order> orders100 = OrderLoader.load(reader100);

        orderAnalyzerOnly2 = new OrderAnalyzerImpl(ordersOnly2);
        orderAnalyzer100 = new OrderAnalyzerImpl(orders100);

        order1 = Order.of("ORD0001,14-03-25,Running Shoes,Footwear,60,3,180,Emma Clark,New York,Debit Card,Cancelled");
        order2 = Order.of("ORD0002,20-03-25,Headphones,Electronics,100,4,400,Emily Johnson,San Francisco,Debit Card,Pending");

        ordersCustom = new ArrayList<>();
        ordersCustom.add(order1);
        ordersCustom.add(order2);
    }

    @Test
    public void allOrdersReturnsCopyOfOrders() {
        List<Order> ordersFromAnalyzer = orderAnalyzerOnly2.allOrders();

        assertNotSame(ordersCustom, ordersFromAnalyzer, "allOrders() must return a copy of the list of orders and not the list itself");
    }

    @Test
    public void ordersByCustomerThrowsIllArgExcWhenCustomerNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> orderAnalyzerOnly2.ordersByCustomer(null), "ordersByCustomer() expects a non-null value for customer");

        assertThrows(IllegalArgumentException.class, () -> orderAnalyzerOnly2.ordersByCustomer(""), "ordersByCustomer() expects a value for customer that isn't blank");
    }

    @Test
    public void ordersByCustomerReturnsCorrectList() {
        List<Order> expectedResult = List.of(order1);

        assertEquals(expectedResult, orderAnalyzerOnly2.ordersByCustomer("Emma Clark"), "Expected ORD0001 when asked for first order");
    }

    @Test
    public void dateWithMostOrdersReturnsNullIfNoOrders() {
        assertNull(new OrderAnalyzerImpl(new ArrayList<>()).dateWithMostOrders(), "Expected null when dateWithMostOrders() used on an OrderAnalyzer with empty orders list");
    }

    @Test
    public void dateWithMostOrdersReturnsCorrectDate() {
        LocalDate date = LocalDate.of(2025,3,15);

        Map.Entry<LocalDate, Long> entry = Map.entry(date, 5L);

        assertEquals(entry, orderAnalyzer100.dateWithMostOrders(), "Expected 2025-03-15=5 answer if dateWithMostOrders() used on orderAnalyzer100");
    }

    @Test
    public void topNMostOrderedProductsThrowsIllArgExcIfnLessThan0() {
        assertThrows(IllegalArgumentException.class, () -> orderAnalyzerOnly2.topNMostOrderedProducts(-1), "Expected IllegalArgumentException if n is -1");
    }

    @Test
    public void topNMostOrderedProductsReturnsEmptyListIfnIs0() {
        assertEquals(new ArrayList<>(), orderAnalyzerOnly2.topNMostOrderedProducts(0), "Expected an empty List when n is 0");
    }

    @Test
    public void topNMostOrderedProductsReturnsCorrectList() {
        List<String> expectedList = List.of("Smartwatch", "Smartphone", "Headphones", "Refrigerator", "Running Shoes");

        assertEquals(expectedList, orderAnalyzer100.topNMostOrderedProducts(5), "Expected [\"Smartwatch\", \"Smartphone\", \"Headphones\", \"Refrigerator\", \"Running Shoes\"] when n is 5 and topNMostOrderedProducts called on orderAnalyzer100");
    }

    @Test
    public void revenueByCategoryReturnsEmptyIfOrdersEmpty() {
        assertEquals(Map.of(), new OrderAnalyzerImpl(List.of()).revenueByCategory());
    }

    @Test
    public void revenueByCategoryReturnsCorrectValue() {
        Map<Category, Double> expected = new HashMap<>();
        expected.put(Category.ELECTRONICS, 400.0);
        expected.put(Category.FOOTWEAR, 180.0);

        assertEquals(expected, orderAnalyzerOnly2.revenueByCategory());
    }

    @Test
    public void suspiciousCustomersReturnsEmptySetIfNoSusCust() {
        assertEquals(Set.of(), orderAnalyzerOnly2.suspiciousCustomers());
    }

}
