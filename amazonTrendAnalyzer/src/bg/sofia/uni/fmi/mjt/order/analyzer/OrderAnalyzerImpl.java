package bg.sofia.uni.fmi.mjt.order.analyzer;

import bg.sofia.uni.fmi.mjt.order.domain.Category;
import bg.sofia.uni.fmi.mjt.order.domain.Order;
import bg.sofia.uni.fmi.mjt.order.domain.PaymentMethod;
import bg.sofia.uni.fmi.mjt.order.domain.Status;

import java.time.LocalDate;
import java.util.*;

public class OrderAnalyzerImpl implements OrderAnalyzer {
    private final List<Order> orders;


    public OrderAnalyzerImpl(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public List<Order> allOrders() {
        return List.copyOf(orders);
    }

    @Override
    public List<Order> ordersByCustomer(String customer) {
        if (customer == null || customer.isBlank()) {
            throw new IllegalArgumentException("Customer is null or blank");
        }

        return orders.stream().filter((order) -> order.customerName().equals(customer)).toList();
    }

    @Override
    public Map.Entry<LocalDate, Long> dateWithMostOrders() {
        Map<LocalDate, Long> container = new HashMap<>();

        orders.forEach((order) -> {
            LocalDate orderDate = order.date();

            if (container.containsKey(orderDate)) {
                container.put(orderDate, container.get(orderDate) + 1);
            } else {
                container.put(orderDate, 1L);
            }
        });

        List<Map.Entry<LocalDate, Long>> result = new ArrayList<>(container.entrySet());

        result.sort(
                Comparator.comparing(
                        Map.Entry<LocalDate, Long>::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey));

        return result.getFirst();
    }

    @Override
    public List<String> topNMostOrderedProducts(int n) {
        n = Math.min(n, orders.size());

        Map<String, Integer> productOrders = new HashMap<>();

        orders.forEach((order) -> {
            String productName = order.product();

            if (productOrders.containsKey(productName)) {
                productOrders.put(productName, productOrders.get(productName) + 1);
            } else {
                productOrders.put(productName, 1);
            }
        });

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(productOrders.entrySet());

        sorted.sort(
                Comparator.comparing(
                        Map.Entry<String, Integer>::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey));

        return sorted.stream().limit(n).map(Map.Entry::getKey).toList();
    }

    @Override
    public Map<Category, Double> revenueByCategory() {
        return Map.of();
    }

    @Override
    public Set<String> suspiciousCustomers() {
        return Set.of();
    }

    @Override
    public Map<Category, PaymentMethod> mostUsedPaymentMethodForCategory() {
        return Map.of();
    }

    @Override
    public String locationWithMostOrders() {
        return "";
    }

    @Override
    public Map<Category, Map<Status, Long>> groupByCategoryAndStatus() {
        return Map.of();
    }
}
