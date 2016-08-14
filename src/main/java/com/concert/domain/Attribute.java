package com.concert.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by himanshu.virmani on 10/08/16.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Attribute implements Serializable{

    @JsonProperty(value = "required")
    @Field(value = "required")
    private boolean required;

    @JsonProperty(value = "type")
    @Field(value = "type")
    private String type;

}
