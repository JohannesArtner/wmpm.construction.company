package com.routes.requestInput.model;

import com.database.clientDB.model.Client;
import com.database.projectDB.model.Request;

/**
 * Entity for Normalized input information
 *
 * Holds a Client and a Request
 */
public class NormalizedInput {
    private Client client;
    private Request request;

    public NormalizedInput() {
    }

    public NormalizedInput(Client client, Request request) {
        this.client = client;
        this.request = request;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "NormalizedInput{" +
                "client=" + client +
                ", request=" + request +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NormalizedInput that = (NormalizedInput) o;

        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        return request != null ? request.equals(that.request) : that.request == null;

    }

    @Override
    public int hashCode() {
        int result = client != null ? client.hashCode() : 0;
        result = 31 * result + (request != null ? request.hashCode() : 0);
        return result;
    }
}
