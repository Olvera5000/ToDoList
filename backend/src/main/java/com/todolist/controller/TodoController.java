package com.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.dto.TodoRequestDTO;
import com.todolist.dto.TodoResponseDTO;
import com.todolist.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    // GET /api/todos - Obtener todas las tareas
    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos() {
        List<TodoResponseDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    
    // GET /api/todos/{id} - Obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> getTodoById(@PathVariable Long id) {
        TodoResponseDTO todo = todoService.getTodoById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    
    // POST /api/todos - Crear una nueva tarea
    @PostMapping
    public ResponseEntity<TodoResponseDTO> createTodo(@Valid @RequestBody TodoRequestDTO requestDTO) {
        TodoResponseDTO createdTodo = todoService.createTodo(requestDTO);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }
    
    // PUT /api/todos/{id} - Actualizar una tarea
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequestDTO requestDTO) {
        TodoResponseDTO updatedTodo = todoService.updateTodo(id, requestDTO);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
    
    // DELETE /api/todos/{id} - Eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // PATCH /api/todos/{id}/toggle - Marcar como completado/pendiente
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TodoResponseDTO> toggleTodo(@PathVariable Long id) {
        TodoResponseDTO toggledTodo = todoService.toggleTodo(id);
        return new ResponseEntity<>(toggledTodo, HttpStatus.OK);
    }
}