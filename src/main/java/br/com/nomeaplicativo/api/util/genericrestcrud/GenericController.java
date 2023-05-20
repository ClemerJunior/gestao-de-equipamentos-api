package br.com.nomeaplicativo.api.util.genericrestcrud;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

/**
 * @param <M> DTO (RETORNO DAS OPERAÇÕES DE CONTROLLER)
 * @param <E> ENTITY (ENTIDADE DO BANCO)
 * @param <K> KEY OF ENTITY (ID DA ENTIDADE)
 */
public abstract class GenericController<M, E, K> implements IGenericController<M, E, K> {

    @Autowired
    protected MapperFacade mapper;

    protected abstract GenericService<E, K> getService();

    private Class<M> dtoType;
    private Class<E> entityType;

    public GenericController() {
        this.dtoType = (Class<M>) getParameterizedTypeByPosition(0);
        this.entityType = (Class<E>) getParameterizedTypeByPosition(1);
    }

    @GetMapping
    public ResponseEntity<PageDTO<M>> findAll(Pageable pageable) {
        return ResponseEntity.ok(mapper.map(getService().findAll(pageable), PageDTO.class));
    }

    @GetMapping(value = "/search/findAllByFilter")
    public ResponseEntity<PageDTO<M>> findAllByFilter(M input, Pageable pageable) {
        return ResponseEntity.ok(mapper.map(getService().findByFilter(dtoToEntity(input), pageable), PageDTO.class));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<M> findById(@PathVariable K id) {
        return getService().findById(id).map(o -> ResponseEntity.ok(entityToDTO(o))).orElseThrow();
    }

    @PostMapping
    public ResponseEntity<M> save(@Validated @RequestBody M input) {
        E entity = getService().save(dtoToEntity(input));
        return new ResponseEntity<>(entityToDTO(entity), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<M> update(@PathVariable K id, M input) {
        return getService().findById(id).map(o -> {
            mapper.map(input, o);
            return ResponseEntity.ok(entityToDTO(getService().save(o)));
        }).orElseThrow();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable K id) {
        return getService().findById(id).map(o -> {
            getService().delete(o);
            return ResponseEntity.noContent().build();
        }).orElseThrow();
    }

    protected M entityToDTO(E entityObject) {
        return mapper.map(entityObject, dtoType);
    }

    protected E dtoToEntity(M dtoObject) {
        return mapper.map(dtoObject, entityType);
    }

    private Class<?> getParameterizedTypeByPosition(int position) {
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[position]);
    }
}
