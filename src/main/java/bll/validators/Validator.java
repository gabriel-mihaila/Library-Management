package bll.validators;

/**
 * The interface Validator.
 *
 * @param <T> the type parameter
 */
public interface Validator<T> {

    /**
     * Validate.
     *
     * @param t the t
     */
    public void validate(T t);
}
