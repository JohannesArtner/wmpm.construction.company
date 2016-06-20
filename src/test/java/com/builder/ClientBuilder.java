package com.builder;

import com.database.clientDB.model.Client;

/**
 * Created by rudolfplettenberg on 20.06.2016.
 */
public class ClientBuilder {
    private Client client = new Client();

    public ClientBuilder id(long id){
        client.setId(id);
        return this;
    }

    public ClientBuilder companyName(String companyName){
        client.setCompanyName(companyName);
        return this;
    }
    public ClientBuilder address(String address){
        client.setAddress(address);
        return this;
    }
    public ClientBuilder telephone(String telephone){
        client.setTelephone(telephone);
        return this;
    }
    public ClientBuilder email(String email){
        client.setEmail(email);
        return this;
    }
    public ClientBuilder clientFirstname(String clientFirstname){
        client.setClientLastname(clientFirstname);
        return this;
    }
    public ClientBuilder clientLastname(String clientLastname){
        client.setClientLastname(clientLastname);
        return this;
    }

    public Client build(){
        return client;
    }
}
