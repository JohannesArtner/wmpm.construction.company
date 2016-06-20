package com.database.clientDB.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by rudolfplettenberg on 05.05.16.
 */
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    private String companyName;
    private String address;
    private String telephone;
    private String email;
    private String clientFirstname;
    private String clientLastname;

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClientFirstname() {
        return clientFirstname;
    }

    public void setClientFirstname(String clientFirstname) {
        this.clientFirstname = clientFirstname;
    }

    public String getClientLastname() {
        return clientLastname;
    }

    public void setClientLastname(String clientLastname) {
        this.clientLastname = clientLastname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", clientFirstname='" + clientFirstname + '\'' +
                ", clientLastname='" + clientLastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (companyName != null ? !companyName.equals(client.companyName) : client.companyName != null) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        if (telephone != null ? !telephone.equals(client.telephone) : client.telephone != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (clientFirstname != null ? !clientFirstname.equals(client.clientFirstname) : client.clientFirstname != null)
            return false;
        return clientLastname != null ? clientLastname.equals(client.clientLastname) : client.clientLastname == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (clientFirstname != null ? clientFirstname.hashCode() : 0);
        result = 31 * result + (clientLastname != null ? clientLastname.hashCode() : 0);
        return result;
    }
}
