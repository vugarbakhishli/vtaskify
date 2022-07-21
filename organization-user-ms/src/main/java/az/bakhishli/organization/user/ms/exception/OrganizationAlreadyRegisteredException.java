package az.bakhishli.organization.user.ms.exception;

import az.bakhishli.common.exception.InvalidStateException;

public class OrganizationAlreadyRegisteredException extends InvalidStateException {

    public static final String MESSAGE = "Organization with given userId [%s] is already registered";

    public OrganizationAlreadyRegisteredException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
