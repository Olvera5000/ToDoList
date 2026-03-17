package com.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.dto.TodoRequestDTO;
import com.todolist.dto.TodoResponseDTO;
import com.todolist.entity.Todo;
import com.todolist.repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;
    
    // Obtener todas las tareas
    public List<TodoResponseDTO> getAllTodos() {
        return todoRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    // Obtener una tarea por ID
    public TodoResponseDTO getTodoById(Long id) {
        return todoRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
    }
    
    // Crear una nueva tarea
    public TodoResponseDTO createTodo(TodoRequestDTO requestDTO) {
        Todo todo = new Todo(
            requestDTO.getTitle(),
            requestDTO.getDescription(),
            requestDTO.getCompleted()
        );
        Todo savedTodo = todoRepository.save(todo);
        return convertToDTO(savedTodo);
    }
    
    // Actualizar una tarea
    public TodoResponseDTO updateTodo(Long id, TodoRequestDTO requestDTO) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
        
        todo.setTitle(requestDTO.getTitle());
        todo.setDescription(requestDTO.getDescription());
        todo.setCompleted(requestDTO.getCompleted());
        
        Todo updatedTodo = todoRepository.save(todo);
        return convertToDTO(updatedTodo);
    }
    
    // Eliminar una tarea
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Tarea no encontrada con ID: " + id);
        }
        todoRepository.deleteById(id);
    }
    
    // Marcar como completado/pendiente
    public TodoResponseDTO toggleTodo(Long id) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
        
        todo.setCompleted(!todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return convertToDTO(updatedTodo);
    }
    
    // Convertir Entity a DTO
    private TodoResponseDTO convertToDTO(Todo todo) {
        return new TodoResponseDTO(
            todo.getId(),
            todo.getTitle(),
            todo.getDescription(),
            todo.getCompleted(),
            todo.getCreatedAt(),
            todo.getUpdatedAt()
        );
    }
}