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
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by himanshu.virmani on 10/08/16.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EntryAttributes implements Serializable{

    @JsonProperty(value = "hero_profile")
    @Field(value = "hero_profile")
    private String heroProfile;

    @JsonProperty(value = "shipment_id")
    @Field(value = "shipment_id")
    private String shipmentId;

    @JsonProperty(value = "task_level")
    @Field(value = "task_level")
    private String taskLevel;

    private String status;

    @JsonProperty(value = "runsheet_id")
    @Field(value = "runsheet_id")
    private String runsheetId;

    @JsonProperty(value = "created_at")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Field(value = "created_at")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime createdAt;

    @JsonProperty(value = "completed_at")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Field(value = "completed_at")
    private DateTime completedAt;
}
