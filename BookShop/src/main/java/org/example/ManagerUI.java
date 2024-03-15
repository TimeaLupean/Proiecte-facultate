package org.example;

import org.example.controllers.*;
import org.example.main.*;
import org.example.main.Patterns.Strategy.BankTransferPaymentStrategy;
import org.example.main.Patterns.Strategy.CardPaymentStrategy;
import org.example.main.Patterns.Strategy.CashPaymentStrategy;
import org.example.main.Patterns.Strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerUI {
    private Scanner scanner = new Scanner(System.in);
    private AuthorController authorController;
    private BookController bookController;
    private CategoryController categoryController;
    private OrdersController ordersController;
    private PublisherController publisherController;
    private ShippingController shippingController;
    private PaymentMethodController paymentMethodController;

    private ReviewController reviewController;
    private ClientController clientController;

    public ManagerUI(AuthorController authorController, BookController bookController, CategoryController categoryController, OrdersController ordersController, PublisherController publisherController, ShippingController shippingController, ClientController clientController, ReviewController reviewController, PaymentMethodController paymentMethodController) {
        this.authorController = authorController;
        this.categoryController = categoryController;
        this.bookController = bookController;
        this.ordersController = ordersController;
        this.publisherController = publisherController;
        this.shippingController = shippingController;
        this.clientController = clientController;
        this.reviewController = reviewController;
        this.paymentMethodController = paymentMethodController;
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
                case 8:
                    reviewMenu();
                case 9:
                    clientsMenu();
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
        System.out.println("4.Update author");
        System.out.println("5.Delete author");
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
                authorController.createAauthor(id, fname, lname, bdate, address);
                break;
            case 2:
                System.out.println("Give ID: ");
                int authorid = scanner.nextInt();
                scanner.nextLine();
                authorController.findAuthorById(authorid);
                break;
            case 3:
                System.out.println("This are the authors: ");
                List<Author> authors = authorController.viewAllAuthors();
                if (authors == null || authors.isEmpty())
                    System.out.println("No authors available.");
                else {
                    for (Author author : authors) {
                        System.out.println("Author ID: " + author.getAuthor_id());
                        System.out.println("Author first name: " + author.getFirstName());
                        System.out.println("Author last name: " + author.getLastName());
                        System.out.println("Author birth date: " + author.getBirth_date());
                        System.out.println("Author address: " + author.getAddress());
                    }
                }
                break;
            case 4:
                System.out.println("Give id: ");
                int aid = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give first name: ");
                String fnamee = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give last name: ");
                String lnamee = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give birth date: ");
                String bdatee = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give address: ");
                String addre = scanner.nextLine();
                scanner.nextLine();
                authorController.updateAuthor(aid, fnamee, lnamee, bdatee, addre);
            case 5:
                System.out.println("Give id: ");
                int autid = scanner.nextInt();
                scanner.nextLine();
                authorController.deleteAuthor(autid);
                System.out.println("Author has been deleted");
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
        System.out.println("4.Update book");
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
                Author author = authorController.findAuthorByName(fname, lname);
                System.out.println("Give price");
                int price = scanner.nextInt();
                ;
                scanner.nextLine();
                System.out.println("Give category type:");
                String type = scanner.nextLine();
                scanner.nextLine();
                Category category = categoryController.findCategoryByType(type);
                bookController.createBook(book_id, book_title, publishing_year, author, price, category);
                break;
            case 2:
                System.out.println("Give book id:");
                int bookId = scanner.nextInt();
                scanner.nextLine();
                bookController.findBookById(bookId);
                break;
            case 3:
                System.out.println("This are all the books: ");
                List<Books> books = bookController.viewAllBooks();
                if (books == null || books.isEmpty()) {
                    System.out.println("No books available.");
                } else {
                    for (Books book : books) {
                        System.out.println("Book ID: " + book.getBook_id());
                        System.out.println("Book title: " + book.getTitle());
                        System.out.println("Book publishing year: " + book.getPublishing_year());
                        System.out.println("Book author: " + book.getAuthor());
                        System.out.println("Book price: " + book.getPrice());
                        System.out.println("Book category: " + book.getCategory());
                    }
                }
                break;
            case 4:
                System.out.println("Give book Id:");
                int book__id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give title:");
                String book__title = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give publishing year:");
                int publishing__year = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give author first name:");
                String finame = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Give author last name: ");
                String laname = scanner.nextLine();
                scanner.nextLine();
                Author author_ = authorController.findAuthorByName(finame, laname);
                System.out.println("Give price");
                int price_ = scanner.nextInt();
                ;
                scanner.nextLine();
                System.out.println("Give category type:");
                String tipe = scanner.nextLine();
                scanner.nextLine();
                Category category_ = categoryController.findCategoryByType(tipe);
                bookController.updateBook(book__id, book__title, publishing__year, author_, price_, category_);
                break;
            case 5:
                System.out.println("Give book Id:");
                int book__id_ = scanner.nextInt();
                scanner.nextLine();
                bookController.deleteBook(book__id_);
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
        System.out.println("4.Update category");
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
                categoryController.createcategory(cat, type);
                break;
            case 2:
                System.out.println("Give category ID: ");
                int catid = scanner.nextInt();
                scanner.nextLine();
                categoryController.findCategoryById(catid);
                break;
            case 3:
                System.out.println("This are the categories: ");
                List<Category> categories = categoryController.viewAllCategory();
                if (categories == null || categories.isEmpty())
                    System.out.println("No categories available.");
                else {
                    for (Category category : categories) {
                        System.out.println("Category ID: " + category.getCategory_id());
                        System.out.println("Category type: " + category.getType());
                    }
                }
                break;
            case 4:
                System.out.println("Give category ID: ");
                int cati = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give category type: ");
                String tipe = scanner.nextLine();
                scanner.nextLine();
                categoryController.updateCategory(cati, tipe);
                break;
            case 5:
                System.out.println("Give category ID: ");
                int categoryid = scanner.nextInt();
                scanner.nextLine();
                categoryController.deletecategory(categoryid);
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
                ordersController.findOrderById(id);
                break;
            case 2:
                System.out.println("These are all the orders: ");
                List<Orders> orders = ordersController.viewAllOrders();
                if (orders == null || orders.isEmpty())
                    System.out.println("There are no orders.");
                else {
                    for (Orders order : orders) {
                        System.out.println("Order Id: " + order.getOrder_id());
                        System.out.println("Order date: " + order.getDate());
                        System.out.println("Total price; " + order.calculateTotalPrice());
                        System.out.println("Client Id: " + order.getClient_id());
                        System.out.println("Status: " + order.getStatus());
                        System.out.println("Cart:");
                        List<CartItem> cartItems = order.getCartItems();
                        for (CartItem cartItem : cartItems) {
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

                Orders order = ordersController.findOrderById(aid);

                System.out.println("Give status: ");
                String status = scanner.nextLine();
                scanner.nextLine();

                ordersController.updateOrder(order.getOrder_id(), order.getDate(), order.calculateTotalPrice(), order.getClient_id(), status, order.getCartItems());
            case 5:
                System.out.println("Give id: ");
                int autid = scanner.nextInt();
                scanner.nextLine();
                authorController.deleteAuthor(autid);
                System.out.println("Order has been deleted");
            case 6:
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
                List<Clients> clients = clientController.viewAllClients();
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
                Clients foundClient = clientController.findClientById(clientIdToFind);
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
                clientController.deleteClient(clientIdToDelete);
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
                Review foundReview = reviewController.findReviewById(reviewIdToFind);
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
                List<Review> reviews = reviewController.viewAllReviews();
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
                reviewController.deleteReview(reviewIdToDelete);
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
                List<PaymentMethod> paymentMethods = paymentMethodController.viewAllPaymentMethods();
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
                PaymentMethod foundPaymentMethod = paymentMethodController.findPaymentMethodById(paymentMethodIdToFind);
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
                System.out.println("Give new type: ");
                String newType = scanner.nextLine();
                PaymentStrategy type;
                if (newType.equalsIgnoreCase("cash")) {
                    type = new CashPaymentStrategy();
                } else {
                    if (newType.equalsIgnoreCase("card")) {
                        type = new CardPaymentStrategy();
                    } else {
                        type = new BankTransferPaymentStrategy();
                    }
                }

                System.out.println("Give new status: ");
                String newStatus = scanner.nextLine();
                paymentMethodController.updatePaymentMethod(paymentMethodIdToUpdate, newStatus, type);
                System.out.println("Payment method with ID " + paymentMethodIdToUpdate + " has been updated.");
                break;
            case 4:
                System.out.println("Give payment method ID to delete: ");
                int paymentMethodIdToDelete = scanner.nextInt();
                scanner.nextLine();
                paymentMethodController.deletePaymentMethod(paymentMethodIdToDelete);
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
        System.out.println("4. Update a publisher");
        System.out.println("5. Delete a publisher");
        System.out.println("6. Exit");
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
                publisherController.createPublisher(publisherId, name, address, fiscalCode,books);
                System.out.println("Publisher added successfully!");
                break;
            case 2:
                System.out.println("Give publisher ID to find: ");
                int publisherIdToFind = scanner.nextInt();
                scanner.nextLine();
                Publisher foundPublisher = publisherController.findPublisherById(publisherIdToFind);
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
                List<Publisher> publishers = publisherController.viewAllPublishers();
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
                System.out.println("Give publisher ID to update: ");
                int publisherIdToUpdate = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give new name: ");
                String newName = scanner.nextLine();
                System.out.println("Give new address: ");
                String newAddress = scanner.nextLine();
                System.out.println("Give new fiscal code: ");
                int newFiscalCode = scanner.nextInt();
                scanner.nextLine();
                List<Books> books1 = new ArrayList<>();
                publisherController.updatePublisher(publisherIdToUpdate, newName, newAddress, newFiscalCode, books1);
                System.out.println("Publisher with ID " + publisherIdToUpdate + " has been updated.");
                break;
            case 5:
                System.out.println("Give publisher ID to delete: ");
                int publisherIdToDelete = scanner.nextInt();
                scanner.nextLine();
                publisherController.deletePublisher(publisherIdToDelete);
                System.out.println("Publisher with ID " + publisherIdToDelete + " has been deleted.");
                break;
            case 6:
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
        System.out.println("3. Update a shipping");
        System.out.println("4. Delete a shipping");
        System.out.println("5. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("These are all the shippings: ");
                List<Shipping> shippingList = shippingController.viewAllShippings();
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
                Shipping foundShipping = shippingController.findShippingById(shippingIdToFind);
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
                System.out.println("Give shipping ID to update: ");
                int shippingIdToUpdate = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Give new shipping method: ");
                String newShippingMethod = scanner.nextLine();
                System.out.println("Give new address: ");
                String newAdress = scanner.nextLine();
                shippingController.updateShipping(shippingIdToUpdate, newAdress, newShippingMethod);
                System.out.println("Shipping with ID " + shippingIdToUpdate + " has been updated.");
                break;
            case 4:
                System.out.println("Give shipping ID to delete: ");
                int shippingIdToDelete = scanner.nextInt();
                scanner.nextLine();
                shippingController.deleteShipping(shippingIdToDelete);
                System.out.println("Shipping with ID " + shippingIdToDelete + " has been deleted.");
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