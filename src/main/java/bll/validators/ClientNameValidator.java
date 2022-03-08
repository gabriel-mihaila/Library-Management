package bll.validators;

import model.Client;

/**
 * The type Client name validator.
 */
public class ClientNameValidator implements Validator<Client> {
    @Override
    public void validate(Client client) {
        if(client.getName() == null || client.getName().length() == 0){
            throw new IllegalArgumentException("The client does not have a name!");
        }
    }
}
