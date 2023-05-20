package br.com.nomeaplicativo.api.util.handles;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ApiModel(value = "Error")
public class ResponseError {

    private int code;
    private String description;
    private Map<String, String> fields = new HashMap<>();
}
