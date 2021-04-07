package com.cattailstore.store.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FullBookDto {

    @NotNull
    public String title;

    public String description;
}
