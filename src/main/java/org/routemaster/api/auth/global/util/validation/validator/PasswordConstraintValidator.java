package org.routemaster.api.auth.global.util.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;
import org.routemaster.api.auth.global.util.validation.constraints.Password;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_SIZE = 8;
    private static final int MAX_SIZE = 16;
    private PasswordValidator validator;

    @Override
    public void initialize(Password constraintAnnotation) {
        Properties props = new Properties();
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("passay.properties");
        try {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MessageResolver resolver = new PropertiesMessageResolver(props);

        validator = new PasswordValidator(resolver, Arrays.asList(
                new LengthRule(MIN_SIZE, MAX_SIZE),

                new CharacterRule(EnglishCharacterData.Alphabetical, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),

                new WhitespaceRule()
        ));
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        RuleResult ruleResult = validator.validate(new PasswordData(password));

        if (ruleResult.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(ruleResult);
        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
