package bll.validators;

import model.Client;

/**
 * The type Client address validator.
 */
public class ClientAddressValidator implements Validator<Client>{
    @Override
    public void validate(Client client) {
        if(client.getAddress() == null || client.getAddress().length() == 0){
            throw new IllegalArgumentException("Address is null!");
        }
    }
}
