package tests;

import org.example.repositories.OrdersRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrdersRepositoryTest {
    @Test
    void findByIdTest_expected() {
        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders expectedOrders = new Orders(12345, "2023-10-29", 100, 1, "Pending", cartItems);
        OrdersRepository OrdersRepository = new OrdersRepository(new ArrayList<>());
        OrdersRepository.save(expectedOrders);
        Orders actualOrders = OrdersRepository.findById(12345);

        assertEquals(expectedOrders, actualOrders, "Failed to find the Orders by Id");
    }

    @Test
    void findByIdTest_unexpected() {

        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders newOrders = new Orders(12345, "2023-10-29", 100, 1, "Pending", cartItems);
        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        OrderssRepository.save(newOrders);
        Orders actualOrders = OrderssRepository.findById(12346);
        Orders expected_output = null;
        assertEquals(expected_output, actualOrders, "Failed to find the Orders by Id");
    }


    @Test
    void findAllTest_expected() {
        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders Orders1 = new Orders(1, "2023-11-01", 100, 1, "Processing", cartItems);
        Orders Orders2 = new Orders(2, "2023-11-02", 150, 2, "Shipped", cartItems);
        Orders Orders3 = new Orders(3, "2023-11-03", 200, 3, "Delivered", cartItems);

        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        OrderssRepository.save(Orders1);
        OrderssRepository.save(Orders2);
        OrderssRepository.save(Orders3);

        List<Orders> foundOrderss = OrderssRepository.findAll();

        assertEquals(3, foundOrderss.size());
        assertTrue(foundOrderss.contains(Orders1));
        assertTrue(foundOrderss.contains(Orders2));
        assertTrue(foundOrderss.contains(Orders3));
    }

    @Test
    void findAllTest_unexpected() {

        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        Orders expected_output = null;
        List<Orders> foundOrderss = OrderssRepository.findAll();

        assertEquals(expected_output, foundOrderss, "Failed to find all");

    }


    @Test
    void saveTest_expected() {

        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders Orders = new Orders(1, "2023-11-01", 100, 1, "Processing", cartItems);
        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        OrderssRepository.save(Orders);

        List<Orders> foundOrderss = OrderssRepository.findAll();
        assertEquals(1, foundOrderss.size());

        Orders savedOrders = foundOrderss.get(0);
        assertEquals(Orders.getOrder_id(), savedOrders.getOrder_id());
        assertEquals(Orders.getDate(), savedOrders.getDate());
        assertEquals(Orders.calculateTotalPrice(), savedOrders.calculateTotalPrice());
        assertEquals(Orders.getClient_id(), savedOrders.getClient_id());
        assertEquals(Orders.getStatus(), savedOrders.getStatus());
    }


    @Test
    void saveTest_unexpected() {

        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders Orders = new Orders(1, "2023-11-01", 100, 1, "Processing", cartItems);
        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        OrderssRepository.save(Orders);

        boolean foundOrders = OrderssRepository.save(Orders);
        boolean expected_output = false;
        assertEquals(expected_output, foundOrders, "Failed to save the Orders");

    }


    @Test
    void updateTest_expected() {
        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders initialOrders = new Orders(123, "2023-11-01", 100, 1, "Processing", cartItems);
        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        OrderssRepository.save(initialOrders);

        Orders updatedOrders = new Orders(123, "2023-11-05", 120, 1, "Shipped", cartItems);
        OrderssRepository.update(updatedOrders);
        boolean expected_output = true;
        boolean result=OrderssRepository.update(updatedOrders);


        assertEquals(result, expected_output, "Failed to update the Orders");
    }


    @Test
    void updateTest_unexpected() {
        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders initialOrders = new Orders(123, "2023-11-01", 100, 1, "Processing", cartItems);
        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        OrderssRepository.save(initialOrders);

        Orders updatedOrders = new Orders(234, "2023-11-05", 120, 1, "Shipped", cartItems);
        OrderssRepository.update(updatedOrders);

        boolean expected_output = false;
        boolean result = OrderssRepository.update(updatedOrders);
        assertEquals(result, expected_output, "Failed to update the Orders");
    }


    @Test
    void deleteTest_expected() {

        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders OrdersToDelete = new Orders(1, "2023-11-01", 100, 1, "Processing", cartItems);
        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        OrderssRepository.save(OrdersToDelete);
        OrderssRepository.delete(1);
        Orders foundOrders = OrderssRepository.findById(1);
        Orders expected_output = null;

        assertEquals(expected_output, foundOrders, " Failed to delete Orders");
    }

    @Test
    void deleteTest_unexpected() {
        Author author1 = new Author(10, "Veronica", "Roth", "19.08.1988", "New York");
        Category category = new Category(1, "Action");
        List<CartItem> books = new ArrayList<>();
        Books book1 = new Books(123, "Chosen One", 2020, author1, 50, category);
        Books book2 = new Books(234, "Divergent", 2011, author1, 45, category);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(book1, 2);
        CartItem cartItem2 = new CartItem(book1, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Orders OrdersToDelete = new Orders(1, "2023-11-01", 100, 1, "Processing", cartItems);
        OrdersRepository OrderssRepository = new OrdersRepository(new ArrayList<>());
        OrderssRepository.save(OrdersToDelete);
        //OrderssRepository.delete(2);
        //Orders foundOrders = OrderssRepository.findById(1);
        boolean deletedOrders = OrderssRepository.delete(2);
        boolean expected_output = false;
        assertEquals(expected_output, deletedOrders, "Failed to delete Orders");

    }
}
