package com.todolist.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDTO {
    
    private Long id;
    
    private String title;
    
    private String description;
    
    private Boolean completed;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}