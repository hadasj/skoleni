package cz.i.validation;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jan.hadas@i.cz
 */
public class Validator {

  @Autowired
  private javax.validation.Validator springValidator;

  public <T>void verify(T dto) {
    Set<ConstraintViolation<T>> violations = springValidator.validate(dto);
    if (!violations.isEmpty()) {
      throw new ValidationException(constructValidationMessage(violations));
    }
  }

  private <T>String constructValidationMessage(Set<ConstraintViolation<T>> violations) {
    String errorMessage = "";

    for (ConstraintViolation violation : violations) {
      if (isNotEmpty(errorMessage)) {
        errorMessage += ", ";
      }
      errorMessage += violation.getPropertyPath() + " " + violation.getMessage();
    }

    return errorMessage;
  }
}
