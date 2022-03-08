package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dao.AbstractDAO;
import exceptions.NotExistException;
import model.Client;

/**
 * The type Client bll.
 */
public class ClientBLL {

    private final List<Validator<Client>> validators;

    /**
     * Instantiates a new Client bll.
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new ClientEmailValidator());
        validators.add(new ClientAgeValidator());
        validators.add(new ClientNameValidator());
        validators.add(new ClientAddressValidator());
    }

    /**
     * Find all clients array list.
     *
     * @return the array list
     */
    public ArrayList<Client> findAllClients(){
        ArrayList<Client> clients = new ArrayList<>(new AbstractDAO<>(Client.class).findAll());
        if(clients.size() == 0) {
            throw new NoSuchElementException("No such client found!");
        }
        return clients;
    }

    /**
     * Delete client.
     *
     * @param field the field
     * @param value the value
     * @throws NotExistException the not exist exception
     */
    public void deleteClient(String field, String value) throws NotExistException {
        int existingItem, oldClientTableSize, newClientTableSize;
        ArrayList<Client> clients = new ArrayList<>(new AbstractDAO<>(Client.class).findAll());
        oldClientTableSize = clients.size();
        existingItem = new AbstractDAO<>(Client.class).delete(field, value);
        clients = new ArrayList<>(new AbstractDAO<>(Client.class).findAll());
        newClientTableSize = clients.size();

        if(existingItem == 1 && oldClientTableSize == newClientTableSize){
            throw new NotExistException("The client doesn't exist in db!");
        }
    }

    /**
     * Insert client.
     *
     * @param client the client
     */
    public void insertClient(Client client) {
        for(Validator<Client> v : validators) {
            v.validate(client);
        }
        new AbstractDAO<>(Client.class).insert(client);
    }

    /**
     * Update client.
     *
     * @param id     the id
     * @param client the client
     * @throws NotExistException the not exist exception
     */
    public void updateClient(int id, Client client) throws NotExistException {
        for(Validator<Client> v : validators){
            v.validate(client);
        }
        Client st = new AbstractDAO<>(Client.class).findById(id);

        if (st == null) {
            throw new NotExistException("The client doesn't exist in db, you cannot update this row!");
        }

        new AbstractDAO<>(Client.class).update(id, client);
    }
}
