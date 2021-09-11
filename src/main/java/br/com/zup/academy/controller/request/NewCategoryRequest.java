package br.com.zup.academy.controller.request;

import br.com.zup.academy.domain.model.Category;
import br.com.zup.academy.domain.repository.CategoryRepository;
import br.com.zup.academy.domain.validation.UniqueValue;
import br.com.zup.academy.domain.validation.ValidateEntity;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Introspected
public class NewCategoryRequest {

    @NotBlank
    @UniqueValue(entity = Category.class, field = "name")
    private String name;
    @ValidateEntity(entity = Category.class, field = "id")
    private UUID motherCategoryId;

    public NewCategoryRequest(String name, UUID motherCategoryId) {
        this.name = name;
        this.motherCategoryId = motherCategoryId;
    }

    public String getName() {
        return name;
    }

    public UUID getMotherCategoryId() {
        return motherCategoryId;
    }

    public Category toDomain(CategoryRepository repository) {
        if(this.motherCategoryId == null)
            return new Category(this.name, null);
        return new Category(this.name, repository.findById(this.motherCategoryId).get());
    }

    @Override
    public String toString() {
        return "NewCategoryRequest{" +
                "name='" + name + '\'' +
                ", motherCategoryId=" + motherCategoryId +
                '}';
    }
}
