package org.example;

import org.example.main.*;
import org.example.repositories.AuthorRepository;
import org.example.repositories.BooksRepository;
import org.example.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Author> AuthorRepo(){
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "Karina", "Moldovan","12.09.1976","Crangului 2"));
        authors.add(new Author(2,"Grigore","Pop","30.11.1854","Bucuresti 99"));
        authors.add(new Author(3,"Anabel","Lautarescu","05.03.1940","Havana 1-4"));
        authors.add(new Author(4,"Hanelore","Minulescu","18.03.1638","Constanta 22"));
        authors.add(new Author(5,"Alexandru","Stoica","14.05.1788","Turdei 193"));
        return authors;
    }

    public static List<Category> CategoryRepo(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1,"Povesti"));
        categories.add(new Category(2,"Actiune"));
        categories.add(new Category(3,"Comedie"));
        categories.add(new Category(4,"Fictiune"));
        categories.add(new Category(5,"Horror"));
        return categories;
    }
    public static List<Books> BookRepo(){
        AuthorRepository authorrepo = new AuthorRepository(AuthorRepo());
        CategoryRepository categoryRepository = new CategoryRepository(CategoryRepo());
        List<Books> books = new ArrayList<>();
        books.add(new Books(1, "Divergent",2000,authorrepo.findById(2),50,categoryRepository.findById(2)));
        books.add(new Books(2,"Labirintul Diavolului", 2020, authorrepo.findById(3),20, categoryRepository.findById(5)));
        books.add(new Books(3,"Mirciulica",2019,authorrepo.findById(5),67,categoryRepository.findById(3)));
        books.add(new Books(4,"Povestea lui HarapAlb",1967,authorrepo.findById(4),33,categoryRepository.findById(1)));
        books.add(new Books(5,"Trezirea lui Nyktos",2022,authorrepo.findById(1),45, categoryRepository.findById(4)));
        return books;
    }
    public static List<Clients> ClientsRepo(){
        List<Clients> clients = new ArrayList<>();
        clients.add(new Clients(1,"Timea","Lupean","11.06.2003","Viile Nadasen 3","lupean_timea@yahoo.com"));
        clients.add(new Clients(2,"Daria","Parlea","20.07.2004","Alverna 98","daria_parlea@yahoo.com"));
        return clients;
    }

    public static List<CartItem> CartItemRepo(){
        List<CartItem> cartItems = new ArrayList<>();
        return cartItems;
    }
    public static List<Orders> OrdersRepo(){
        List<Orders> orders = new ArrayList<>();
        return orders;
    }
    public static List<PaymentMethod> PaymentRepo(){
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        return paymentMethods;
    }
    public static List<Review> ReviewRepo(){
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1,5,"magaziul perfect",2,"12.11.2023"));
        return reviews;
    }
    public static List<Shipping> ShippingRepo(){
        List<Shipping> shippings = new ArrayList<>();
        return shippings;
    }
    public static List<Publisher> PublisherRepo(){
        BooksRepository booksRepository= new BooksRepository(BookRepo());
        List<Books> books1= new ArrayList<>();
        books1.add(booksRepository.findById(1));
        List<Books> books2= new ArrayList<>();
        books2.add(booksRepository.findById(2));
        List<Books> books3= new ArrayList<>();
        books3.add(booksRepository.findById(3));
        List<Books> books4= new ArrayList<>();
        books4.add(booksRepository.findById(4));
        List<Books> books5= new ArrayList<>();
        books5.add(booksRepository.findById(5));

        List<Publisher> publishers=new ArrayList<>();
        publishers.add(new Publisher(1,"Arthur","Splaiul Independentei 16",13965909,books1));
        publishers.add(new Publisher(2,"Paralela 45","Bulevardul Republicii",6494981,books2));
        publishers.add(new Publisher(3,"Carusel","Lipscani 55",30455888,books3));
        publishers.add(new Publisher(4,"Flamingo","Miercani 34 e",6505177,books4));
        publishers.add(new Publisher(5,"Elefant","Dimitrie Pompeiu 7",26396066,books5));
        return publishers;
    }
    public static void main(String[] args) {
//
//        PersonFactory personFactory = new PersonFactory();
//        AuthorRepository authorRepository = new AuthorRepository(AuthorRepo());
//        BooksRepository booksRepository = new BooksRepository(BookRepo());
//        CartItemRepository cartItemRepository = new CartItemRepository(CartItemRepo());
//        CategoryRepository categoryRepository = new CategoryRepository(CategoryRepo());
//        ClientsRepository clientsRepository = new ClientsRepository(ClientsRepo());
//        OrdersRepository ordersRepository = new OrdersRepository(OrdersRepo());
//        PaymentMethodRepository paymentMethodRepository = new PaymentMethodRepository(PaymentRepo());
//        PublisherRepository publisherRepository = new PublisherRepository(PublisherRepo());
//        ReviewRepository reviewRepository = ReviewRepository.getInstance(ReviewRepo());
//        ShippingRepository shippingRepository = new ShippingRepository(ShippingRepo());
//
//        PersonController personController = new PersonController(personFactory,authorRepository,clientsRepository);
//        AuthorController authorController = new AuthorController(authorRepository,personController);
//        BookController bookController = new BookController(booksRepository);
//        CartController cartController = new CartController(cartItemRepository);
//        CategoryController categoryController = new CategoryController(categoryRepository);
//        ClientController clientController = new ClientController(clientsRepository,personController);
//        OrdersController ordersController = new OrdersController(ordersRepository);
//        PaymentMethodController paymentMethodController = new PaymentMethodController(paymentMethodRepository);
//        PublisherController publisherController = new PublisherController(publisherRepository);
//        ReviewController reviewController = new ReviewController(reviewRepository);
//        ShippingController shippingController = new ShippingController(shippingRepository);
//
//        ClientUI clientUI = new ClientUI(bookController,ordersController,clientController,reviewController,paymentMethodController, categoryController,authorController, shippingController);
//        //clientUI.start();
//
//        ManagerUI managerUI = new ManagerUI(authorController,bookController,categoryController,ordersController,publisherController,shippingController,clientController,reviewController,paymentMethodController);
//        managerUI.start();

    }
}