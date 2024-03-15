package org.example;

import org.example.BD_Controller.*;
import org.example.BD_Repository.*;

import java.util.Scanner;

public class MainBD {

    public static void main(String[] args){
        final Scanner scanner = new Scanner(System.in);
        final SqlServer sqlServer= new SqlServer();
        AuthorRepositoryBD authorRepositoryBD = new AuthorRepositoryBD(sqlServer);
        BooksRepositoryDB booksRepositoryDB = new BooksRepositoryDB(sqlServer);
        CartItemRepositoryBD cartItemRepositoryBD = new CartItemRepositoryBD(sqlServer);
        CategoryRepositoryBD categoryRepositoryBD = new CategoryRepositoryBD(sqlServer);
        ClientsRepositoryBD clientsRepositoryBD = new ClientsRepositoryBD(sqlServer);
        OrdersRepositoryBD ordersRepositoryBD = new OrdersRepositoryBD(sqlServer);
        PaymentMethodRepositoryBD paymentMethodRepositoryBD = new PaymentMethodRepositoryBD(sqlServer);
        PublisherRepositoryBD publisherRepositoryBD = new PublisherRepositoryBD(sqlServer);
        ReviewRepositoryBD reviewRepositoryBD = ReviewRepositoryBD.getInstance(sqlServer);
        ShippingRepositoryBD shippingRepositoryBD = new ShippingRepositoryBD(sqlServer);

        AuthorControllerBD authorControllerBD = new AuthorControllerBD(authorRepositoryBD);
        BooksControllerBD booksControllerBD = new BooksControllerBD(booksRepositoryDB);
        CartItemControllerBD cartItemControllerBD = new CartItemControllerBD(cartItemRepositoryBD);
        CategoryControllerBD categoryControllerBD = new CategoryControllerBD(categoryRepositoryBD);
        ClientsControllerBD clientsControllerBD = new ClientsControllerBD(clientsRepositoryBD);
        OrdersControllerBD ordersControllerBD = new OrdersControllerBD(ordersRepositoryBD);
        PaymentMethodControllerBD paymentMethodControllerBD = new PaymentMethodControllerBD(paymentMethodRepositoryBD);
        PublisherControllerBD publisherControllerBD = new PublisherControllerBD(publisherRepositoryBD);
        ReviewControllerBD reviewControllerBD = new ReviewControllerBD(reviewRepositoryBD);
        ShippingControllerBD shippingControllerBD = new ShippingControllerBD(shippingRepositoryBD);

        ClientUIBD clientUIBD = new ClientUIBD(booksControllerBD,ordersControllerBD,clientsControllerBD,reviewControllerBD,paymentMethodControllerBD,categoryControllerBD,authorControllerBD,shippingControllerBD,cartItemControllerBD);


        ManagerUIDB managerUIDB = new ManagerUIDB(authorControllerBD,booksControllerBD,categoryControllerBD,ordersControllerBD,publisherControllerBD,shippingControllerBD,paymentMethodControllerBD,reviewControllerBD,clientsControllerBD);


        System.out.println("Do ypu want to access the manager menu or the client menu?(client/manager)");
        String choice = scanner.nextLine();
        switch (choice){
            case "client":
                clientUIBD.start();
                return;
            case "manager":
                managerUIDB.start();
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }


    }

}
