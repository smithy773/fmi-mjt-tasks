package bg.sofia.uni.fmi.mjt.order.loader;

import bg.sofia.uni.fmi.mjt.order.domain.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class OrderLoader {

    /**
     * Returns a list of orders read from the source Reader.
     *
     * @param reader the Reader with orders
     * @throws IllegalArgumentException if the reader is null
     */
    public static List<Order> load(Reader reader) throws IOException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader is null");
        }

        BufferedReader br = new BufferedReader(reader);

        List<Order> result = br.lines().skip(1).map(Order::of).toList();

        br.close();

        return result;
    }
}
