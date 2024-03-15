package org.example;

import org.example.BD_Controller.*;
import org.example.main.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerUIDB {
    private Scanner scanner = new Scanner(System.in);
    private AuthorControllerBD authorController;
    private BooksControllerBD bookController;
    private CategoryControllerBD categoryController;
    private OrdersControllerBD ordersController;
    private PublisherControllerBD publisherController;
    private ShippingControllerBD shippingController;
    private PaymentMethodControllerBD paymentMethodController;
    private ReviewControllerBD reviewController;
    private ClientsControllerBD clientController;

    public ManagerUIDB(AuthorControllerBD authorController, BooksControllerBD bookController, CategoryControllerBD categoryController, OrdersControllerBD ordersController, PublisherControllerBD publisherController, ShippingControllerBD shippingController, PaymentMethodControllerBD paymentMethodController, ReviewControllerBD reviewController, ClientsControllerBD clientController) {
        this.authorController = authorController;
        this.bookController = bookController;
        this.categoryController = categoryController;
        this.ordersController = ordersController;
        this.publisherController = publisherController;
        this.shippingController = shippingController;
        this.paymentMethodController = paymentMethodController;
        this.reviewController = reviewController;
        this.clientController = clientController;
    }

    public void start() {
        while (true) {
            System.out.println("1. Manage authors");
            System.out.println("2. Manage books");
            System.out.println("3. Manage categories");
            System.out.println("4. Manage orders");
            System.out.println("5. Manage publishers");
            System.out.println("6. Manage shipping");
            System.out.println("7. Manage payment methods");
            System.out.println("8. Manage reviews");
            System.out.println("9. Manage clients");
            System.out.println("10.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    authorsMenu();
                    break;
                case 2:
                    booksMenu();
                    break;
                case 3:
                    categoryMenu();
                    break;
                case 4:
                    ordersMenu();
                    break;
                case 5:
                    publisherMenu();
                    break;
                case 6:
                    shippingMenu();
                    break;
                case 7:
                    paymentMethodMenu();
                    break;
                case 8:
                    reviewMenu();
                    break;
                case 9:
                    clientsMenu();
                    break;
                case 10:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    public void authorsMenu() {
        System.out.println("1.Add author");
        System.out.println("2.Find author by ID");
        System.out.println("3.View all authors");
        System.out.println("4.Delete author");
        System.out.println("5.Find author by name");
        System.out.println("6. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Give id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give first name: ");
                String fname = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give last name: ");
                String lname = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give birth date: ");
                String bdate = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give address: ");
                String address = scanner.nextLine();
                scanner.nextLine();
                Author author = new Author(id,fname,lname,bdate,address);
                authorController.saveIntoDB(author);
                break;
            case 2:
                System.out.println("Give ID: ");
                int authorid = scanner.nextInt();
                scanner.nextLine();
                Author author2 = authorController.findById(authorid);
                System.out.println("Author ID: " + author2.getAuthor_id());
                System.out.println("Author first name: " + author2.getFirstName());
                System.out.println("Author last name: " + author2.getLastName());
                System.out.println("Author birth date: " + author2.getBirth_date());
                System.out.println("Author address: " + author2.getAddress());
                break;
            case 3:
                System.out.println("This are the authors: ");
                List<Author> authors = authorController.loadFromDB();
                if (authors == null || authors.isEmpty())
                    System.out.println("No authors available.");
                else {
                    for (Author author1 : authors) {
                        System.out.println("Author ID: " + author1.getAuthor_id());
                        System.out.println("Author first name: " + author1.getFirstName());
                        System.out.println("Author last name: " + author1.getLastName());
                        System.out.println("Author birth date: " + author1.getBirth_date());
                        System.out.println("Author address: " + author1.getAddress());
                    }
                }
                break;
            case 4:
                System.out.println("Give id: ");
                int autid = scanner.nextInt();
                scanner.nextLine();
                authorController.delete(autid);
                System.out.println("Author has been deleted");
                break;
            case 5:
                System.out.println("Give first name: ");
                String FirstName = scanner.nextLine();
                System.out.println("Give last name: ");
                String LastName = scanner.nextLine();
                Author author3 = authorController.findByName(FirstName,LastName);
                System.out.println("Author ID: " + author3.getAuthor_id());
                System.out.println("Author first name: " + author3.getFirstName());
                System.out.println("Author last name: " + author3.getLastName());
                System.out.println("Author birth date: " + author3.getBirth_date());
                System.out.println("Author address: " + author3.getAddress());
                break;
            case 6:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }


    public void booksMenu() {
        System.out.println("1.Add new boooks");
        System.out.println("2. Find book by Id");
        System.out.println("3.Find all books");
        System.out.println("4.Update book price");
        System.out.println("5.Delete book");
        System.out.println("6. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Give book Id:");
                int book_id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give title:");
                String book_title = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give publishing year:");
                int publishing_year = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give author first name:");
                String fname = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give author last name: ");
                String lname = scanner.nextLine();
                scanner.nextLine();
                Author author = authorController.findByName(fname, lname);
                System.out.println("Give price");
                int price = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give category type:");
                String type = scanner.nextLine();
                scanner.nextLine();
                Category category = categoryController.findByType(type);
                Books book = new Books (book_id, book_title, publishing_year, author, price, category);
                bookController.saveIntoDB(book);
                break;
            case 2:
                System.out.println("Give book id:");
                int bookId = scanner.nextInt();
                scanner.nextLine();
                Books book2 = bookController.findById(bookId);
                System.out.println("Book ID: " + book2.getBook_id());
                System.out.println("Book title: " + book2.getTitle());
                System.out.println("Book publishing year: " + book2.getPublishing_year());
                System.out.println("Book author: " + book2.getAuthor());
                System.out.println("Book price: " + book2.getPrice());
                System.out.println("Book category: " + book2.getCategory());
                break;
            case 3:
                System.out.println("This are all the books: ");
                List<Books> books = bookController.loadFromDB();
                if (books == null || books.isEmpty()) {
                    System.out.println("No books available.");
                } else {
                    for (Books book1 : books) {
                        System.out.println("Book ID: " + book1.getBook_id());
                        System.out.println("Book title: " + book1.getTitle());
                        System.out.println("Book publishing year: " + book1.getPublishing_year());
                        System.out.println("Book author: " + book1.getAuthor());
                        System.out.println("Book price: " + book1.getPrice());
                        System.out.println("Book category: " + book1.getCategory());
                    }
                }
                break;
            case 4:
                System.out.println("Give book Id:");
                int book__id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give price");
                int price_ = scanner.nextInt();
                scanner.nextLine();
                bookController.updatePrice(book__id,price_);
                break;
            case 5:
                System.out.println("Give book Id:");
                int book__id_ = scanner.nextInt();
                scanner.nextLine();
                bookController.delete(book__id_);
                System.out.println("Book has been deleted");
                break;
            case 6:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }


    public void categoryMenu() {
        System.out.println("1.Add category");
        System.out.println("2.Find category by ID");
        System.out.println("3.View all categories");
        System.out.println("4.Find category by type");
        System.out.println("5.Delete category");
        System.out.println("6. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Give category ID: ");
                int cat = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give category type: ");
                String type = scanner.nextLine();
                scanner.nextLine();
                Category category = new Category(cat, type);
                categoryController.saveIntoDB(category);
                break;
            case 2:
                System.out.println("Give category ID: ");
                int catid = scanner.nextInt();
                scanner.nextLine();
                Category category2 = categoryController.findById(catid);
                System.out.println("Category ID: " + category2.getCategory_id());
                System.out.println("Category type: " + category2.getType());
                break;
            case 3:
                System.out.println("This are the categories: ");
                List<Category> categories = categoryController.loadFromDB();
                if (categories == null || categories.isEmpty())
                    System.out.println("No categories available.");
                else {
                    for (Category category1 : categories) {
                        System.out.println("Category ID: " + category1.getCategory_id());
                        System.out.println("Category type: " + category1.getType());
                    }
                }
                break;
            case 4:
                System.out.println("Give category type: ");
                String tipe = scanner.nextLine();
                scanner.nextLine();
                Category category3 = categoryController.findByType(tipe);
                System.out.println("Category ID: " + category3.getCategory_id());
                System.out.println("Category type: " + category3.getType());
                break;
            case 5:
                System.out.println("Give category ID: ");
                int categoryid = scanner.nextInt();
                scanner.nextLine();
                categoryController.delete(categoryid);
                System.out.println("category has been deleted");
                break;
            case 6:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }


    public void ordersMenu() {
        System.out.println("1.Find order by id");
        System.out.println("2.View all orders");
        System.out.println("3.Update order status");
        System.out.println("4.Delete order");
        System.out.println("5. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Give id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                Orders orders = ordersController.findById(id);
                System.out.println("Order Id: " + orders.getOrder_id());
                System.out.println("Order date: " + orders.getDate());
                System.out.println("Total price; " + orders.calculateTotalPrice());
                System.out.println("Client Id: " + orders.getClient_id());
                System.out.println("Status: " + orders.getStatus());
                System.out.println("Cart:");
                List<CartItem> cartItems = orders.getCartItems();
                for (CartItem cartItem : cartItems) {
                    System.out.println("Book: " + cartItem.getBook().getTitle());
                    System.out.println("Quantity: " + cartItem.getQuantity());
                }
                break;
            case 2:
                System.out.println("These are all the orders: ");
                List<Orders> orders1 = ordersController.loadFromDB();
                if (orders1 == null || orders1.isEmpty())
                    System.out.println("There are no orders.");
                else {
                    for (Orders order : orders1) {
                        System.out.println("Order Id: " + order.getOrder_id());
                        System.out.println("Order date: " + order.getDate());
                        System.out.println("Total price; " + order.calculateTotalPrice());
                        System.out.println("Client Id: " + order.getClient_id());
                        System.out.println("Status: " + order.getStatus());
                        System.out.println("Cart:");
                        List<CartItem> cartItems1 = order.getCartItems();
                        for (CartItem cartItem : cartItems1) {
                            System.out.println("Book: " + cartItem.getBook().getTitle());
                            System.out.println("Quantity: " + cartItem.getQuantity());
                        }
                    }
                }
                break;
            case 3:

                System.out.println("Give order id: ");
                int aid = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Give status: ");
                String status = scanner.nextLine();
                scanner.nextLine();

                ordersController.updateStatus(aid,status);
                break;
            case 4:
                System.out.println("Give id: ");
                int ordid = scanner.nextInt();
                scanner.nextLine();
                ordersController.delete(ordid);
                System.out.println("Order has been deleted");
                break;
            case 5:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }


    public void clientsMenu() {
        System.out.println("1. View all clients");
        System.out.println("2. Find client by ID");
        System.out.println("3. Delete a client");
        System.out.println("4. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("These are all the clients: ");
                List<Clients> clients = clientController.loadFromDB();
                if (clients == null || clients.isEmpty())
                    System.out.println("No clients available.");
                else {
                    for (Clients client : clients) {
                        System.out.println("Client Id: " + client.getClient_id());
                        System.out.println("Client first name: " + client.getFirstName());
                        System.out.println("Client last name: " + client.getLastName());
                        System.out.println("Client birth date: " + client.getBirth_date());
                        System.out.println("Client address: " + client.getAddress());
                        System.out.println("Client email: " + client.getEmail());
                    }
                }
                break;
            case 2:
                System.out.println("Give client Id to find the client: ");
                int clientIdToFind = scanner.nextInt();
                scanner.nextLine();
                Clients foundClient = clientController.findById(clientIdToFind);
                if (foundClient != null) {
                    System.out.println("Client found:");
                    System.out.println("Client Id: " + foundClient.getClient_id());
                    System.out.println("Client first name: " + foundClient.getFirstName());
                    System.out.println("Client last name: " + foundClient.getLastName());
                    System.out.println("Client birth date: " + foundClient.getBirth_date());
                    System.out.println("Client address: " + foundClient.getAddress());
                    System.out.println("Client email: " + foundClient.getEmail());
                } else {
                    System.out.println("Client not found with Id " + clientIdToFind);
                }
                break;
            case 3:
                System.out.println("Give client Id to delete: ");
                int clientIdToDelete = scanner.nextInt();
                scanner.nextLine();
                clientController.delete(clientIdToDelete);
                System.out.println("Client with Id " + clientIdToDelete + " has been deleted.");
                break;
            case 4:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }


    public void reviewMenu() {
        System.out.println("1. Find review by ID");
        System.out.println("2. View all reviews");
        System.out.println("3. Delete a review");
        System.out.println("4. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Give review Id to find: ");
                int reviewIdToFind = scanner.nextInt();
                scanner.nextLine();
                Review foundReview = reviewController.findById(reviewIdToFind);
                if (foundReview != null) {
                    System.out.println("Review found:");
                    System.out.println("Review Id: " + foundReview.getReview_id());
                    System.out.println("Stars Number: " + foundReview.getStars_number());
                    System.out.println("Feedback: " + foundReview.getFeedback());
                    System.out.println("Book Id: " + foundReview.getBook_id());
                    System.out.println("Date: " + foundReview.getDate());
                } else {
                    System.out.println("Review not found with Id " + reviewIdToFind);
                }
                break;
            case 2:
                System.out.println("These are all the reviews: ");
                List<Review> reviews = reviewController.loadFromDB();
                if (reviews == null || reviews.isEmpty())
                    System.out.println("No reviews available.");
                else {
                    for (Review review : reviews) {
                        System.out.println("Review Id: " + review.getReview_id());
                        System.out.println("Stars Number: " + review.getStars_number());
                        System.out.println("Feedback: " + review.getFeedback());
                        System.out.println("Book Id: " + review.getBook_id());
                        System.out.println("Date: " + review.getDate());
                    }
                }
                break;
            case 3:
                System.out.println("Give review Id to delete: ");
                int reviewIdToDelete = scanner.nextInt();
                scanner.nextLine();
                reviewController.delete(reviewIdToDelete);
                System.out.println("Review with Id " + reviewIdToDelete + " has been deleted.");
                break;
            case 4:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }


    public void paymentMethodMenu() {
        System.out.println("1. View all payment methods");
        System.out.println("2. Find payment method by ID");
        System.out.println("3. Update a payment method");
        System.out.println("4. Delete a payment method");
        System.out.println("5. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("These are all the payment methods: ");
                List<PaymentMethod> paymentMethods = paymentMethodController.loadFromDB();
                if (paymentMethods == null || paymentMethods.isEmpty())
                    System.out.println("No payment methods available.");
                else {
                    for (PaymentMethod paymentMethod : paymentMethods) {
                        System.out.println("Payment Method ID: " + paymentMethod.getPayment_id());
                        System.out.println("Status: " + paymentMethod.getStatus());
                    }
                }
                break;
            case 2:
                System.out.println("Give payment method ID to find: ");
                int paymentMethodIdToFind = scanner.nextInt();
                scanner.nextLine();
                PaymentMethod foundPaymentMethod = paymentMethodController.findById(paymentMethodIdToFind);
                if (foundPaymentMethod != null) {
                    System.out.println("Payment method found:");
                    System.out.println("Payment Method ID: " + foundPaymentMethod.getPayment_id());
                    System.out.println("Status: " + foundPaymentMethod.getStatus());
                } else {
                    System.out.println("Payment method not found with ID " + paymentMethodIdToFind);
                }
                break;

            case 3:
                System.out.println("Give payment method ID to update: ");
                int paymentMethodIdToUpdate = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Give new status: ");
                String newStatus = scanner.nextLine();
                paymentMethodController.updateStatus(paymentMethodIdToUpdate, newStatus);
                System.out.println("Payment method with ID " + paymentMethodIdToUpdate + " has been updated.");
                break;
            case 4:
                System.out.println("Give payment method ID to delete: ");
                int paymentMethodIdToDelete = scanner.nextInt();
                scanner.nextLine();
                paymentMethodController.delete(paymentMethodIdToDelete);
                System.out.println("Payment method with ID " + paymentMethodIdToDelete + " has been deleted.");
                break;
            case 5:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }


    public void publisherMenu() {
        System.out.println("1. Add a new publisher");
        System.out.println("2. Find publisher by ID");
        System.out.println("3. View all publishers");
        System.out.println("4. Delete a publisher");
        System.out.println("5. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Give publisher ID: ");
                int publisherId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give publisher name: ");
                String name = scanner.nextLine();
                System.out.println("Give publisher address: ");
                String address = scanner.nextLine();
                System.out.println("Give publisher fiscal code: ");
                int fiscalCode = scanner.nextInt();
                scanner.nextLine();
                List<Books> books = new ArrayList<>();
                Publisher publisher2 = new Publisher(publisherId, name, address, fiscalCode,books);
                System.out.println("Publisher added successfully!");
                break;
            case 2:
                System.out.println("Give publisher ID to find: ");
                int publisherIdToFind = scanner.nextInt();
                scanner.nextLine();
                Publisher foundPublisher = publisherController.findById(publisherIdToFind);
                if (foundPublisher != null) {
                    System.out.println("Publisher found:");
                    System.out.println("Publisher ID: " + foundPublisher.getPublisher_id());
                    System.out.println("Name: " + foundPublisher.getName());
                    System.out.println("Address: " + foundPublisher.getAddress());
                    System.out.println("Fiscal Code: " + foundPublisher.getFiscal_code());
                } else {
                    System.out.println("Publisher not found with ID " + publisherIdToFind);
                }
                break;
            case 3:
                System.out.println("These are all the publishers: ");
                List<Publisher> publishers = publisherController.loadFromDB();
                if (publishers == null || publishers.isEmpty())
                    System.out.println("No publishers available.");
                else {
                    for (Publisher publisher : publishers) {
                        System.out.println("Publisher ID: " + publisher.getPublisher_id());
                        System.out.println("Name: " + publisher.getName());
                        System.out.println("Address: " + publisher.getAddress());
                        System.out.println("Fiscal Code: " + publisher.getFiscal_code());
                    }
                }
                break;
            case 4:
                System.out.println("Give publisher ID to delete: ");
                int publisherIdToDelete = scanner.nextInt();
                scanner.nextLine();
                publisherController.delete(publisherIdToDelete);
                System.out.println("Publisher with ID " + publisherIdToDelete + " has been deleted.");
                break;
            case 5:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }


    public void shippingMenu() {
        System.out.println("1. View all shippings");
        System.out.println("2. Find shipping by ID");
        System.out.println("3. Delete a shipping");
        System.out.println("4. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("These are all the shippings: ");
                List<Shipping> shippingList = shippingController.loadFromDB();
                if (shippingList == null || shippingList.isEmpty())
                    System.out.println("No shipping available.");
                else {
                    for (Shipping shipping : shippingList) {
                        System.out.println("Shipping ID: " + shipping.getShipping_id());
                        System.out.println("Address: " + shipping.getAddress());
                        System.out.println("Shipping Method: " + shipping.getShipping_method());
                    }
                }
                break;
            case 2:
                System.out.println("Give shipping ID to find: ");
                int shippingIdToFind = scanner.nextInt();
                scanner.nextLine();
                Shipping foundShipping = shippingController.findById(shippingIdToFind);
                if (foundShipping != null) {
                    System.out.println("Shipping found:");
                    System.out.println("Shipping ID: " + foundShipping.getShipping_id());
                    System.out.println("Address: " + foundShipping.getAddress());
                    System.out.println("Shipping Method: " + foundShipping.getShipping_method());
                } else {
                    System.out.println("Shipping not found with ID " + shippingIdToFind);
                }
                break;
            case 3:
                System.out.println("Give shipping ID to delete: ");
                int shippingIdToDelete = scanner.nextInt();
                scanner.nextLine();
                shippingController.delete(shippingIdToDelete);
                System.out.println("Shipping with ID " + shippingIdToDelete + " has been deleted.");
                break;
            case 4:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

}