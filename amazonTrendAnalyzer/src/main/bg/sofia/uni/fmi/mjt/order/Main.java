package bg.sofia.uni.fmi.mjt.order;

import bg.sofia.uni.fmi.mjt.order.analyzer.OrderAnalyzerImpl;
//import bg.sofia.uni.fmi.mjt.order.domain.Order;
import bg.sofia.uni.fmi.mjt.order.loader.OrderLoader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
//import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/amazon_sales_data_2025.csv";

        Reader reader = new FileReader(filePath);

        var orders = OrderLoader.load(reader);
        System.out.println(orders.size());

        var analyzer = new OrderAnalyzerImpl(orders);

//        String filePath100 = "src/test/resources/amazon_sales_data_2025.csv";

//        Reader reader100 = new FileReader(filePath100);

//        List<Order> orders100 = OrderLoader.load(reader100);

//        var orderAnalyzer100 = new OrderAnalyzerImpl(orders100);

//        System.out.println(orderAnalyzer100.suspiciousCustomers());
//        System.out.println("======================================");
//        System.out.println(orderAnalyzer100.locationWithMostOrders());
//        System.out.println("======================================");
//        System.out.println(orderAnalyzer100.groupByCategoryAndStatus());
//        System.out.println("======================================");
//        System.out.println(orderAnalyzer100.mostUsedPaymentMethodForCategory());
//        System.out.println("======================================");
//        System.out.println(orderAnalyzer100.topNMostOrderedProducts(5));
//        System.out.println("======================================");
//        System.out.println(orderAnalyzer100.ordersByCustomer("Chris White"));
//        System.out.println("======================================");
//        System.out.println(orderAnalyzer100.dateWithMostOrders());
//        System.out.println("======================================");
//        System.out.println(orderAnalyzer100.revenueByCategory());

        System.out.println(analyzer.suspiciousCustomers());
        System.out.println("======================================");
        System.out.println(analyzer.locationWithMostOrders());
        System.out.println("======================================");
        System.out.println(analyzer.groupByCategoryAndStatus());
        System.out.println("======================================");
        System.out.println(analyzer.mostUsedPaymentMethodForCategory());
        System.out.println("======================================");
        System.out.println(analyzer.topNMostOrderedProducts(2));
        System.out.println("======================================");
        System.out.println(analyzer.ordersByCustomer("Chris White"));
        System.out.println("======================================");
        System.out.println(analyzer.dateWithMostOrders());
        System.out.println("======================================");
        System.out.println(analyzer.revenueByCategory());
    }
}