package com.cattailstore.store.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

    @Min(value = 1900, message = "Enter the proper year date (should be between 1900 and current year)",
        groups = {UpdateInfo.class})
    @Max(value = 2100, message = "Enter the proper year date (should be between 1900 and current year)",
        groups = {UpdateInfo.class})
    public int year;
}
