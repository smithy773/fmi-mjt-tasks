package bg.sofia.uni.fmi.mjt.order.analyzer;

import bg.sofia.uni.fmi.mjt.order.domain.Category;
import bg.sofia.uni.fmi.mjt.order.domain.Order;
import bg.sofia.uni.fmi.mjt.order.domain.PaymentMethod;
import bg.sofia.uni.fmi.mjt.order.domain.Status;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

        return orders.stream().filter(
                (order) -> order.customerName()
                        .equals(customer))
                .toList();
    }

    @Override
    public Map.Entry<LocalDate, Long> dateWithMostOrders() {
        if (orders.isEmpty()) {
            return null;
        }

        Map<LocalDate, Long> container = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::date,
                        Collectors.counting()
                ));

//        Map<LocalDate, Long> container = new HashMap<>();
//
//        orders.forEach(order -> {
//            container.merge(order.date(), 1L, Long::sum);
//        });

        return container.entrySet().stream()
                .max(Comparator.comparing(Map.Entry<LocalDate, Long>::getValue)
                        .thenComparing(Map.Entry::getKey, Comparator.reverseOrder()))
                .orElse(null);
    }

    @Override
    public List<String> topNMostOrderedProducts(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (n == 0) {
            return new ArrayList<>();
        }

//        Map<String, Integer> productOrders = new HashMap<>();
//
//        orders.forEach((order) -> {
//            productOrders.merge(order.product(), 1, Integer::sum);
//        });

        Map<String, Integer> productOrders = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::product,
                        Collectors.summingInt(order -> 1)
                ));

        n = Math.min(n, productOrders.size());

        return productOrders.entrySet().stream()
                .sorted(Comparator.comparing(
                                Map.Entry<String, Integer>::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey))
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }

    @Override
    public Map<Category, Double> revenueByCategory() {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.summingDouble(Order::totalSales)
                ));
    }

    @Override
    public Set<String> suspiciousCustomers() {
//        Map<String, List<Order>> container = new HashMap<>();
//
//        orders.forEach((order) -> {
//            String customer = order.customerName();
//
//            if (order.status() == Status.CANCELLED &&
//            order.totalSales() < 100) {
//
//                if (!container.containsKey(customer)) {
//                    container.put(customer, new ArrayList<>());
//                }
//
//                container.get(customer).add(order);
//            }
//        });

        return orders.stream()
                .filter(
                        order -> order.status() == Status.CANCELLED &&
                                order.totalSales() < 100
                ).collect(
                        Collectors.groupingBy(
                                Order::customerName,
                                Collectors.counting()
                        ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

//        Set<String> susCustomers = new HashSet<>();
//
//        container.forEach((customer, susOrders) -> {
//            if (susOrders.size() > 3) {
//                susCustomers.add(customer);
//            }
//        });
//
//        return susCustomers;
    }

    @Override
    public Map<Category, PaymentMethod> mostUsedPaymentMethodForCategory() {
//        Map<Category, Map<PaymentMethod, Integer>> container = new HashMap<>();
//
//        orders.forEach(order -> {
//            Category category = order.category();
//            PaymentMethod paymentMethod = order.paymentMethod();
//
//            if (container.containsKey(category)) {
//                Map<PaymentMethod, Integer> subMap = container.get(category);
//
//                if (subMap.containsKey(paymentMethod)) {
//                    subMap.put(paymentMethod, subMap.get(paymentMethod) + 1);
//                } else {
//                    subMap.put(paymentMethod, 1);
//                }
//
//            } else {
//                container.put(category, new HashMap<>());
//                container.get(category).put(paymentMethod, 1);
//            }
//        });

        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Order::paymentMethod,
                                        Collectors.counting()
                                ),
                                subMap -> subMap.entrySet().stream()
                                        .max(Comparator.comparing(Map.Entry<PaymentMethod, Long>::getValue)
                                                .thenComparing(entry -> entry.getKey().name(),
                                                        Comparator.reverseOrder()
                                                ))
                                        .orElseThrow()
                                        .getKey()
                        )
                ));

//        Map<Category, PaymentMethod> result = new HashMap<>();
//
//        container.forEach((category, subMap) -> {
//            Optional<Map.Entry<PaymentMethod, Integer>> mostUsedPaymentMethodEntry = subMap.entrySet().stream()
//                    .max(Comparator.comparing(Map.Entry<PaymentMethod, Integer>::getValue).thenComparing(entry -> entry.getKey().name(), Comparator.reverseOrder()));
//
//           PaymentMethod mostUsedPaymentMethod = null;
//
//            if (mostUsedPaymentMethodEntry.isPresent()) {
//                mostUsedPaymentMethod = mostUsedPaymentMethodEntry.get().getKey();
//            }
//
//            result.put(category, mostUsedPaymentMethod);
//        });
//
//        return result;
    }

    @Override
    public String locationWithMostOrders() {
//        Map<String, Integer> container = new HashMap<>();
//
//        orders.forEach(order -> {
//            String location = order.customerLocation();
//
//            if (container.containsKey(location)) {
//                container.put(location, container.get(location) + 1);
//            } else {
//                container.put(location, 1);
//            }
//        });
//
//
//
//        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(container.entrySet());
//
//        sorted.sort(Comparator.comparing(
//                Map.Entry<String, Integer>::getValue, Comparator.reverseOrder())
//                .thenComparing(Map.Entry::getKey));

        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customerLocation,
                        Collectors.counting()
                ))
                .entrySet()
                .stream().max(Comparator.comparing(
                                Map.Entry<String, Long>::getValue)
                        .thenComparing(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    @Override
    public Map<Category, Map<Status, Long>> groupByCategoryAndStatus() {
//        Map<Category, Map<Status, Long>> result = new HashMap<>();
//
//        orders.forEach(order -> {
//            Category category = order.category();
//            Status status = order.status();
//
//            if (result.containsKey(category)) {
//                Map<Status, Long> subMap = result.get(category);
//
//                if (subMap.containsKey(status)) {
//                    subMap.put(status, subMap.get(status) + 1);
//                } else {
//                    subMap.put(status, 1L);
//                }
//
//            } else {
//                result.put(category, new HashMap<>());
//                result.get(category).put(status, 1L);
//            }
//        });
//
//        return result;


        return orders.stream()
                .collect(
                        Collectors.groupingBy(
                                Order::category,
                                Collectors.groupingBy(
                                        Order::status,
                                        Collectors.counting()
                                )
                        ));
    }
}
