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
}
