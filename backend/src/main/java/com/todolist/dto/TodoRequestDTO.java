package com.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDTO {
    
    @NotBlank(message = "El título no puede estar vacío")
    private String title;
    
    private String description;
    
    private Boolean completed;
}