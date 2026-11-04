package bg.sofia.uni.fmi.mjt.order.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record Order(String id, LocalDate date, String product, Category category, double price, int quantity, double totalSales, String customerName, String customerLocation, PaymentMethod paymentMethod, Status status) {

    /**
     * Static method that returns a new Order object from a single String line input in the amazon sales CSV format.
     *
     * @param line - String in the amazon sales CSV format
     * @return Order object with properties equal to the line input (id is OrderID, date is order date, etc.)
     */
    public static Order of(String line) {
        String[] orderArr = line.split(",");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yy");

        String id = orderArr[0];
        LocalDate date = LocalDate.parse(orderArr[1], dtf);
        String product = orderArr[2];

        Category category = switch (orderArr[3]) {
            case "Electronics" -> Category.ELECTRONICS;
            case "Clothing" -> Category.CLOTHING;
            case "Footwear" -> Category.FOOTWEAR;
            case "Home Appliances" -> Category.HOME_APPLIANCES;
            case "Books" -> Category.BOOKS;
            default -> null;
        };

        double price = Double.parseDouble(orderArr[4]);
        int quantity = Integer.parseInt(orderArr[5]);
        double totalSales = Double.parseDouble(orderArr[6]);
        String customerName = orderArr[7];
        String customerLocation = orderArr[8];

        PaymentMethod paymentMethod = switch(orderArr[9]) {
            case "Credit Card" -> PaymentMethod.CREDIT_CARD;
            case "Debit Card" -> PaymentMethod.DEBIT_CARD;
            case "PayPal" -> PaymentMethod.PAYPAL;
            case "Amazon Pay" -> PaymentMethod.AMAZON_PAY;
            case "Gift Card" -> PaymentMethod.GIFT_CARD;
            default -> null;
        };

        Status status = switch(orderArr[10]) {
            case "Completed" -> Status.COMPLETED;
            case "Pending" -> Status.PENDING;
            case "Cancelled" -> Status.CANCELLED;
            default -> null;
        };

        return new Order(id, date, product, category, price, quantity, totalSales, customerName, customerLocation, paymentMethod, status);
    }
}
