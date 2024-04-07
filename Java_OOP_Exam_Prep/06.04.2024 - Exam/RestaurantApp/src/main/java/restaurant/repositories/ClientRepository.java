package restaurant.repositories;

import restaurant.models.client.Client;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClientRepository implements Repository<Client> {
    private Map<String, Client> clients;

    public ClientRepository() {
        this.clients = new LinkedHashMap<>();
    }

    @Override
    public Collection<Client> getCollection() {
        return Collections.unmodifiableCollection(this.clients.values());
    }

    @Override
    public void add(Client client) {
        this.clients.put(client.getName(), client);
    }

    @Override
    public boolean remove(Client client) {
        if(this.clients.containsKey(client.getName())){
            this.clients.remove(client.getName());
            return true;
        }
        return false;
    }

    @Override
    public Client byName(String name) {
        if(this.clients.containsKey(name)){
            return this.clients.get(name);
        }
        return null;
    }
}
