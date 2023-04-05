package crud.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AbilitiesResponse {
    private Boolean isHidden;
    private Ability ability;
    private Integer slot;


    @Data
    @Builder
    public static class Ability{
        private String name;
        private String url;
    }
}
