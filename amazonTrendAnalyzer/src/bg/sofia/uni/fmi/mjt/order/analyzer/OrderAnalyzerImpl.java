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
        Map<Category, Double> result = new HashMap<>();

        orders.forEach((order) -> {
            Category category = order.category();
            Double totalSales = order.totalSales();

            if (result.containsKey(category)) {
                result.put(category, result.get(category) + totalSales);
            } else {
                result.put(category, totalSales);
            }
        });

        return result;
    }

    @Override
    public Set<String> suspiciousCustomers() {
        Map<String, List<Order>> container = new HashMap<>();

        orders.forEach((order) -> {
            String customer = order.customerName();

            if (order.status() == Status.CANCELLED &&
            order.totalSales() < 100) {

                if (!container.containsKey(customer)) {
                    container.put(customer, new ArrayList<>());
                }

                container.get(customer).add(order);
            }
        });

        Set<String> susCustomers = new HashSet<>();

        container.forEach((customer, susOrders) -> {
            if (susOrders.size() >= 3) {
                susCustomers.add(customer);
            }
        });

        return susCustomers;
    }

    @Override
    public Map<Category, PaymentMethod> mostUsedPaymentMethodForCategory() {


        return Map.of();
    }

    @Override
    public String locationWithMostOrders() {
        Map<String, Integer> container = new HashMap<>();

        orders.forEach(order -> {
            String location = order.customerLocation();

            if (container.containsKey(location)) {
                container.put(location, container.get(location) + 1);
            } else {
                container.put(location, 1);
            }
        });

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(container.entrySet());

        sorted.sort(Comparator.comparing(
                        Map.Entry<String, Integer>::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey));

        return sorted.getFirst().getKey();
    }

    @Override
    public Map<Category, Map<Status, Long>> groupByCategoryAndStatus() {
        Map<Category, Map<Status, Long>> result = new HashMap<>();

        orders.forEach(order -> {
            Category category = order.category();
            Status status = order.status();

            if (result.containsKey(category)) {
                Map<Status, Long> subMap = result.get(category);

                if (subMap.containsKey(status)) {
                    subMap.put(status, subMap.get(status) + 1);
                } else {
                    subMap.put(status, 1L);
                }

            } else {
                result.put(category, new HashMap<>());
            }
        });

        return result;
    }
}
