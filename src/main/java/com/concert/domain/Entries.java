/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.concert.domain;

import com.concert.domain.util.CustomDateTimeDeserializer;
import com.concert.domain.util.CustomDateTimeSerializer;
import com.concert.repository.redis.RedisJsonMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.IOException;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Document
public class Entries implements Serializable, RedisJsonMapper<Entries>{

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @JsonProperty(value = "actor_id")
    @Field(value = "actor_id")
    private String actorId;

    private String type;

    private int version;

    @JsonProperty(value = "attributes")
    @Field(value = "attributes")
    private EntryAttributes entryAttributes;

    @JsonProperty(value = "transaction_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Field(value = "transaction_date")
    private DateTime transactionDate;

    @Override
    public String toJsonString() {
        final ObjectMapper jacksonObjectMapper = new ObjectMapper();
        try {
            return jacksonObjectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entries fromJson(String json) {
        final ObjectMapper jacksonObjectMapper = new ObjectMapper();
        try {
            return jacksonObjectMapper.readValue(json, Entries.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
