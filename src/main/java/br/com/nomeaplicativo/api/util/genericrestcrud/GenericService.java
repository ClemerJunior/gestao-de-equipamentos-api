package br.com.nomeaplicativo.api.util.genericrestcrud;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @param <E> ENTITY (ENTIDADE DO BANCO)
 * @param <K> KEY OF ENTITY (ID DA ENTIDADE)
 */
public abstract class GenericService<E, K> {

    protected abstract JpaRepository<E, K> getRepository();

    /**
     * Realiza a busca de um registro por id
     * @param id
     * @return O Registro pesquisado
     */
    public Optional<E> findById(final K id) {
        return getRepository().findById(id);
    }

    /**
     * Realiza a busca de todos os registros de forma paginada
     * @param pageable
     * @return A lista de registros
     */
    public Page<E> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }


    /**
     * Realiza a pesquisa por filtro de forma paginada
     * @param input
     * @param pageable
     * @return
     */
    public Page<E> findByFilter(final E input, Pageable pageable) {
        Example<E> example = Example.of(input, getExampleMatcher());
        return getRepository().findAll(example, pageable);
    }

    /**
     * Persiste o registro recebido
     * @param entity
     * @return A Entidade persistida
     */
    public E save(final E entity) {
        E saved = getRepository().save(preSave(entity));
        return postSave(saved);
    }

    /**
     * Realiza o "delete" do registro
     * @param entity
     */
    public void delete(final E entity) {
        getRepository().delete(entity);
    }

    /**
     * Realiza alguma ação antes do "save" ser executado.
     * @param entity
     * @return A Entidade a ser persistida
     */
    protected E preSave(final E entity) {
        return entity;
    }

    /**
     * Realiza alguma ação após a execução do "save".
     * @param entity
     * @return A Entidade após ser persistida
     */
    protected E postSave(final E entity){
        return entity;
    }

    /**
     * Realiza a pesquisa por igualdade ignorando case
     * @return
     */
    protected  ExampleMatcher getExampleMatcher(){
        return ExampleMatcher.matchingAll().withIgnoreCase();
    }

}
