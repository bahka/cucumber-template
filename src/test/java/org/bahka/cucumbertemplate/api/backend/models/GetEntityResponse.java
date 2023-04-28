package org.bahka.cucumbertemplate.api.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetEntityResponse {
    public Integer field;
    public List<SubEntity> entityList;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubEntity {
        public String field;
    }
}
