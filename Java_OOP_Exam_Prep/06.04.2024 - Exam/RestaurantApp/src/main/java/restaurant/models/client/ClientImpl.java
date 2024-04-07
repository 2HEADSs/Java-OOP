package restaurant.models.client;

import restaurant.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;

public class ClientImpl implements Client{
    private String name;
    private Collection<String> clientOrders;

    public ClientImpl(String name) {
        this.setName(name);
        this.clientOrders = new ArrayList<>();
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.CLIENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getClientOrders() {
        return this.clientOrders;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
