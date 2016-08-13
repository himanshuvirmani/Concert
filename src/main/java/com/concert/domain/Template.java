package com.concert.domain;

import com.concert.domain.util.CustomDateTimeDeserializer;
import com.concert.domain.util.CustomDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by himanshu.virmani on 10/08/16.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Template implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private int version;

    @JsonProperty(value = "created_at")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Field(value = "created_at")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime createdAt;

    @JsonProperty(value = "updated_at")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Field(value = "updated_at")
    private DateTime updatedAt;

    @Field(value = "attributes")
    @JsonProperty(value = "attributes")
    private Map<String,Attribute> attributeMap;

}
