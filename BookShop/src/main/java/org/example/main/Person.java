package org.example.main;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String birth_date;
    private String address;

    public Person(int id,String firstName, String lastName, String birth_date, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth_date = birth_date;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
