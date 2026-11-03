package bg.sofia.uni.fmi.mjt.order;

import bg.sofia.uni.fmi.mjt.order.analyzer.OrderAnalyzerImpl;
import bg.sofia.uni.fmi.mjt.order.loader.OrderLoader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/resources/amazon_sales_data_2025.csv";

        Reader reader = new FileReader(filePath);

        var orders = OrderLoader.load(reader);
        System.out.println(orders.size());

        var analyzer = new OrderAnalyzerImpl(orders);

        System.out.println(analyzer.suspiciousCustomers());
        System.out.println("======================================");
        System.out.println(analyzer.locationWithMostOrders());
        System.out.println("======================================");
        System.out.println(analyzer.groupByCategoryAndStatus());
        System.out.println("======================================");
        System.out.println(analyzer.mostUsedPaymentMethodForCategory());
        System.out.println("======================================");
        System.out.println(analyzer.topNMostOrderedProducts(1));
        System.out.println("======================================");
        System.out.println(analyzer.ordersByCustomer("Chris White"));
        System.out.println("======================================");
        System.out.println(analyzer.dateWithMostOrders());
        System.out.println("======================================");
        System.out.println(analyzer.revenueByCategory());
    }
}