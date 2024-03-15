package org.example;
import org.example.controllers.*;
import org.example.main.*;
import org.example.main.Patterns.Strategy.BankTransferPaymentStrategy;
import org.example.main.Patterns.Strategy.CardPaymentStrategy;
import org.example.main.Patterns.Strategy.CashPaymentStrategy;
import org.example.main.Patterns.Strategy.PaymentStrategy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

public class ClientUI {
    private final Scanner scanner = new Scanner(System.in);

    private Clients currentClient;
    private int orderid = 0;
    private int reviewID = 0;
    private final BookController bookController;
    private final OrdersController ordersController;
    private final ClientController clientController;
    private final ReviewController reviewController;
    private final PaymentMethodController paymentMethodController;
    private final CategoryController categoryController;
    private final AuthorController authorController;
    private final ShippingController shippingController;


    public ClientUI(BookController bookController, OrdersController ordersController, ClientController clientController, ReviewController reviewController, PaymentMethodController paymentMethodController, CategoryController categoryController, AuthorController authorController, ShippingController shippingController) {
        this.bookController = bookController;
        this.ordersController = ordersController;
        this.clientController = clientController;
        this.reviewController = reviewController;
        this.paymentMethodController = paymentMethodController;
        this.categoryController = categoryController;
        this.authorController = authorController;
        this.shippingController = shippingController;
    }


    public void start(){
        while(true){
            System.out.println("Welcome! Please log in or register first.");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Update data");
            System.out.println("4.Go to bookshop");
            System.out.println("5.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    bookshop();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void bookshop(){
        while (true){
            System.out.println("Welcome to Bookshop!");
            System.out.println("1.View Books");
            System.out.println("2.View authors");
            System.out.println("3.View categories");
            System.out.println("4.Add Book to Cart");
            System.out.println("5.Delete book form cart");
            System.out.println("6.View Cart");
            System.out.println("7.Leave review");
            System.out.println("8.Finalize order");
            System.out.println("9.Exit");
            System.out.println("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    viewBooks();
                    break;
                case 2:
                    viewAuthors();
                    break;
                case 3:
                    viewCategories();
                    break;
                case 4:
                    addBookToCart();
                    break;
                case 5:
                    deleteBookFromCart();
                    break;
                case 6:
                    viewCart();
                    break;
                case 7:
                    leaveReview();
                    break;
                case 8:
                    finalizeOrder();
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    public void leaveReview(){
        reviewID++;
        System.out.println("Give book id: ");
        int bookid = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Give number of stars(1-5): ");
        int stars = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Give feedback: ");
        String feedback = scanner.nextLine();
        scanner.nextLine();
        String date = getCurrentDateAsString();
        reviewController.createReview(reviewID, stars, feedback, bookid, date);
    }

    public void viewCategories(){
        System.out.println("List of available categories:");
        List<Category> availablecategories = categoryController.viewAllCategory();
        int index = 1;
        if(availablecategories == null || availablecategories.isEmpty()){
            System.out.println("No categories available.");
        } else {
            for(Category category :availablecategories){
                System.out.println(index + ": " + category.getType());
                index++;
            }
        }
    }

    public void viewAuthors(){
        System.out.println("List of available Authors:");
        List<Author> availableauthors = authorController.viewAllAuthors();
        int index = 1;
        if(availableauthors == null || availableauthors.isEmpty()){
            System.out.println("No authors available");
        } else{
            for(Author author:availableauthors){
                System.out.println(index + ": " + author.getFirstName() + " " + author.getLastName());
                index++;
            }
        }
    }
    public void viewBooks(){
        System.out.println("List of available books:");
        List<Books> availableBooks = bookController.viewAllBooks();
        if(availableBooks == null ||availableBooks.isEmpty()){
            System.out.println("No books available.");
        } else {
            for(Books book : availableBooks){
                System.out.println("Book ID: " + book.getBook_id());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Publishing year: " + book.getPublishing_year());
                System.out.println("Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
                System.out.println("Price: " + book.getPrice());
                System.out.println("Category: " + book.getCategory().getType());
            }
        }
    }

    public void addBookToCart() {
        if (currentClient != null) {
            System.out.println("Enter the book ID to add to the cart: ");
            int bookid = scanner.nextInt();
            scanner.nextLine();
            Books selectedbook = bookController.findBookById(bookid);

            if (selectedbook != null) {
                selectedbook.getDiscountedPrice();
                System.out.println("Price: " + selectedbook.getPrice());
                System.out.println("Enter the quantity: ");
                int quantity = scanner.nextInt();
                CartItem cartItem = new CartItem(selectedbook, quantity);
                ordersController.addItemToOrder(orderid, cartItem);
                System.out.println("Book added to cart.");
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("Please log in before adding a book to the cart.");
        }
    }


    public void deleteBookFromCart() {
        if (currentClient != null) {
            System.out.println("Enter the book id you want to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Books bookToDelete = bookController.findBookById(id);
            Orders order = ordersController.findOrderById(orderid);

            if (bookToDelete != null) {
                List<CartItem> cartItems = order.getCartItems();
                Iterator<CartItem> iterator = cartItems.iterator();

                while (iterator.hasNext()) {
                    CartItem cartItem = iterator.next();
                    if (bookToDelete == cartItem.getBook()) {
                        if (cartItem.getQuantity() == 1) {
                            iterator.remove(); // Use iterator's remove method
                            ordersController.removeItemFromOrder(orderid, cartItem);
                        } else {
                            int quantity = cartItem.getQuantity();
                            cartItem.setQuantity(quantity - 1);
                        }
                    }
                }
            }
        }
    }


    public void finalizeOrder(){
        if(currentClient != null){
            Orders order = ordersController.findOrderById(orderid);
            order.setStatus("processing");

            System.out.println("How would you like to pay?(cahs/card/bank transfer): ");
            String pay_method = scanner.nextLine();
            scanner.nextLine();
            PaymentStrategy type;
            if (pay_method.equalsIgnoreCase("cash")) {
                type = new CashPaymentStrategy();
                type.processPayment();
            } else {
                if (pay_method.equalsIgnoreCase("card")) {
                    type = new CardPaymentStrategy();
                } else {
                    type = new BankTransferPaymentStrategy();
                }
            }

            if(pay_method.equalsIgnoreCase("cash")){
                paymentMethodController.createPaymentMethod(1,"at delevery",type);
            } else if (pay_method.equalsIgnoreCase("card")) {
                paymentMethodController.createPaymentMethod(2,"paid",type);
            } else{
                paymentMethodController.createPaymentMethod(3,"waiting",type);
            }

            System.out.println("What shipping method would you like?(postal office, courier): ");
            String shipping_method = scanner.nextLine();
            scanner.nextLine();
            System.out.println("At what address?: ");
            String shipping_address = scanner.nextLine();
            scanner.nextLine();
            if(shipping_method.equalsIgnoreCase("postal office")){
                shippingController.createShipping(orderid, shipping_address,"postal office");
            } else {
                shippingController.createShipping(orderid, shipping_address, "courier");
            }

            ordersController.createOrder(orderid,getCurrentDateAsString(),currentClient.getClient_id(),order.calculateTotalPrice(),"processing",order.getCartItems());
            System.out.println("Order finalized. Thank you for your purchase!");
        } else {
            System.out.println("Please log in before finalizing the order.");
        }
    }

    public void viewCart(){
        if(currentClient != null){
            Orders order = ordersController.findOrderById(orderid);
            List<CartItem> cartItems = order.getCartItems();
            if(cartItems.isEmpty()){
                System.out.println("Your shopping cart is empty.");
            } else {
                System.out.println("Your shopping cart: ");
                for (CartItem cartItem : cartItems) {
                    System.out.println("Book: " + cartItem.getBook().getTitle());
                    System.out.println("Quantity: " + cartItem.getQuantity());
                }
            }
        }else {
            System.out.println("Please log in to view your shopping cart.");
        }
    }

    public void register() {
        System.out.print("Enter a user ID: ");
        int user_id = scanner.nextInt();
        scanner.nextLine();

        if (clientController.findClientById(user_id) != null) {
            System.out.println("Client ID is already in use. Please choose a different one.");
            return;
        }
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter your first name: ");
        String fname = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lname = scanner.nextLine();

        System.out.println("Enter your birth date: ");
        String birthdate = scanner.nextLine();

        System.out.println("Enter your address: ");
        String address = scanner.nextLine();

        clientController.createClient(user_id,fname,lname,birthdate,address,email);

        System.out.println("Registration successful. You can now log in.");
        //System.out.println("Number of clients in the repository: " + clientsRepository.findAll().size());
    }

    public static String getCurrentDateAsString() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return currentDate.format(formatter);
    }

    public static String getCurrentDayOfWeek() {
        LocalDate currentDate = LocalDate.now();

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        Locale locale = Locale.getDefault();
        String dayOfWeekString = dayOfWeek.getDisplayName(TextStyle.FULL, locale);

        return dayOfWeekString;
    }


    public void login() {
        System.out.print("Enter your client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Clients client = clientController.findClientById(clientId);

        if (client != null && client.getEmail().equals(email)) {
            orderid +=1;
            String date = getCurrentDateAsString();
            List<CartItem> empty = new ArrayList<>();
            ordersController.createOrder(orderid, date,0,clientId,"initialized",empty);

            currentClient = client;
            System.out.println("Login successful. Welcome, Client ID " + currentClient.getClient_id() + "!");
        } else {
            System.out.println("Invalid client ID or email. Please try again.");
        }
    }

    public void updateData(){
        System.out.print("Enter your ID: ");
        int user_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter your first name: ");
        String fname = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lname = scanner.nextLine();

        System.out.println("Enter your birth date: ");
        String birthdate = scanner.nextLine();

        System.out.println("Enter your address: ");
        String address = scanner.nextLine();

        clientController.updateClient(user_id, email, fname, lname, birthdate, address);
    }

}