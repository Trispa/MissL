package io.gotech.missl.persistence;

public interface EntityDTOTransformer<E, D> {
    E toEntity(D dto);

    D toDTO(E entity);
}
