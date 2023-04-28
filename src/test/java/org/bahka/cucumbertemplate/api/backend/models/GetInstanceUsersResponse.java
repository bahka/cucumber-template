package org.bahka.cucumbertemplate.api.backend.models;

import java.util.List;

public class GetInstanceUsersResponse {

    public List<InstanceUser> pagedRecords;
    public Integer totalRecords;

    public static class InstanceUser {
        public String email;
        public String identity;
        public String name;
    }
}
