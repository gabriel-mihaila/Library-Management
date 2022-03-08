package bll.validators;

import model.Client;

/**
 * The type Client age validator.
 */
public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 60;

    public void validate(Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The ClientAge limit is not respected!");
        }

    }

}

