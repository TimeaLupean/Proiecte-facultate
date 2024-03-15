package org.example.main;



public class Clients extends Person{
    private String email;

    public Clients(int client_id, String firstName, String lastName, String birth_date, String addresses, String email) {
        super(client_id,firstName,lastName,birth_date,addresses);
        this.email = email;
    }

    public int getClient_id() {
        return super.getId();
    }
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
