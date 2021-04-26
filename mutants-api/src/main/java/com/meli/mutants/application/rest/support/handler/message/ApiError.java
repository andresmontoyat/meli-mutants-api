package com.meli.mutants.application.rest.support.handler.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("API Error Message")
public class ApiError<T> {

    public static final String API_ERROR_DEFAULT_CODE = "000000";

    @ApiModelProperty(name = "code")
    @JsonProperty("code")
    private String code;

    @ApiModelProperty(name = "message")
    @JsonProperty("message")
    private String message;

    @ApiModelProperty(name = "errors")
    @JsonProperty("errors")
    private T errors;

    @Builder
    public ApiError(String code, String message, T errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }
}
