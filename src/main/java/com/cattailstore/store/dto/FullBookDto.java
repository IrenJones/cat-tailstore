package com.cattailstore.store.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FullBookDto {

    @NotBlank(message = "Title cannot be empty", groups = {UpdateInfo.class})
    public String title;

    @NotBlank(message = "Author cannot be empty", groups = {UpdateInfo.class})
    public String author;

    public String description;
}
