package org.genc.app.SneakoAplication.dto;


import jakarta.annotation.Nullable;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CategoryDTO {

    @Nullable
    private Long categoryID;

    @NotBlank(message = "Enter the category name")
    private String name;


}
