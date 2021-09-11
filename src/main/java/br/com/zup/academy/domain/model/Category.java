package br.com.zup.academy.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @JoinColumn(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Category motherCategory;

    @Deprecated
    public Category() {
    }

    public Category(String name, Category motherCategory) {
        this.name = name;
        this.motherCategory = motherCategory;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
