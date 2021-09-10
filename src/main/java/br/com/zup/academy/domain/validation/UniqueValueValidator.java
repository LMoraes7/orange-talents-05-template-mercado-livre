package br.com.zup.academy.domain.validation;

import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private EntityManager manager;

    public UniqueValueValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    @Transactional
    public boolean isValid(Object value, AnnotationValue<UniqueValue> annotationMetadata, ConstraintValidatorContext context) {
        this.logger.info("start of single value validation process");

        String field = annotationMetadata.get("field", String.class).get();
        String entity = annotationMetadata.get("entity", Class.class).get().getSimpleName();

        this.logger.info("performing entity search -> " + entity + " by field -> " + field);

        String jpql = "SELECT x FROM " + entity + " x WHERE x." + field + " = :value";

        var isValid = this.manager.createQuery(jpql).setParameter("value", value).getResultList().isEmpty();

        if (isValid) {
            this.logger.info("validation approved for entity -> " + entity + " by field -> " + field);
            return true;
        }
        this.logger.info("validation failed for entity -> " + entity + " by field -> " + field);
        return false;
    }
}
