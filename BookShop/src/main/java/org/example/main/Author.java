package org.example.main;


public class Author extends Person{


    public Author(int author_id, String firstName, String lastName, String birth_date, String address) {
        super(author_id,firstName,lastName,birth_date,address);

    }

    public int getAuthor_id() {
        return super.getId();
    }

    @Override
    public String toString() {
        return "Author{" +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }
}
