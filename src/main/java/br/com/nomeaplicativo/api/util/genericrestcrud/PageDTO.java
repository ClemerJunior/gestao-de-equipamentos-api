package br.com.nomeaplicativo.api.util.genericrestcrud;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel(value = "Page")
public class PageDTO<T> {

    private List<T> items = new ArrayList<>();

    private int pageNumber;
    private int pageSize;

    private int totalPages;
    private int totalElements;
}
