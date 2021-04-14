package com.cattailstore.store.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class FullBookDto {

    @NotBlank(message = "Title cannot be empty", groups = {UpdateInfo.class})
    public String title;

    @NotBlank(message = "Title cannot be empty", groups = {UpdateInfo.class})
    public String subtitle;

    @NotBlank(message = "Author cannot be empty", groups = {UpdateInfo.class})
    public String author;

    public String description;

    @Size(min = 1900, max = 2100)
    int year;
}
