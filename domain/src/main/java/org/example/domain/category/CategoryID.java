package org.example.domain.category;

import org.example.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {
    private final String value;

    private CategoryID(final String value) {
        this.value = value;
    }

    // metodo fabrica para gerar um novo uuid unico.
    public static CategoryID unique() {
        return CategoryID.from(UUID.randomUUID());
    }

    // metodo para criar um CategoryID vindo de uma string.
    // geralmente usado pelo banco.
    public static CategoryID from(final String anId) {
       return new CategoryID(anId);
    }

    // criar um CategoryID vindo de um uuid.
    public static  CategoryID from(final UUID anId){
        return new CategoryID(anId.toString().toLowerCase());
    }

    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryID that = (CategoryID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
