package br.com.nomeaplicativo.api.util.genericrestcrud;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @param <M> DTO (RETORNO DAS OPERAÇÕES DE CONTROLLER)
 * @param <E> ENTITY (ENTIDADE DO BANCO)
 * @param <K> KEY OF ENTITY (ID DA ENTIDADE)
 */
public interface IGenericController<M, E, K> {

    @PostMapping()
    ResponseEntity<M> save(@Validated @RequestBody M input);

    @GetMapping()
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
            value = "Pagina de resultados que desejar obter.", defaultValue = "0"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
            value = "Número de registros por página.", defaultValue = "5"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
            value = "Ordenação no seguinte formato: propriedade(asc|desc)." +
                "<br/>Ordenação padrão é ASC." +
                "<br/>Ordenação múltipla é permitida.")})
    ResponseEntity<PageDTO<M>> findAll(
        @ApiIgnore("Ignorado devido o Swagger exibir os parametros incorretos.")
        @PageableDefault(sort = "id") Pageable pageable);

    @GetMapping(value = "/search/find-by-filter")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
            value = "Pagina de resultados que desejar obter.", defaultValue = "0"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
            value = "Número de registros por página.", defaultValue = "5"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
            value = "Ordenação no seguinte formato: propriedade(asc|desc)." +
                "<br/>Ordenação padrão é ASC." +
                "<br/>Ordenação múltipla é permitida.")})
    ResponseEntity<PageDTO<M>> findAllByFilter(M input,
                                               @ApiIgnore("Ignorado devido o Swagger exibir os parametros incorretos.")
                                               @PageableDefault(sort = "id") Pageable pageable);

    @GetMapping(value = "{id}")
    ResponseEntity<M> findById(@PathVariable K id);

    @PutMapping(value = "{id}")
    ResponseEntity<M> update(@PathVariable K id, @Validated @RequestBody M input);

    @DeleteMapping(value = "{id}")
    ResponseEntity<?> delete(@PathVariable K id);

}
