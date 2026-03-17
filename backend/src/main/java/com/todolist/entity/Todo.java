package com.todolist.entity;
//esto siempre se pone porque es el paquete donde se encuentra la clase, 
//en este caso es el paquete "entity" dentro del proyecto "todolist"

//ahora vamos a instanciar las librerias necesarias para crear la clase "todo", que es la clase que
//representa una tarea en nuestra aplicación de lista de tareas. Esta clase tendrá atributos como "id", 
//"title", "description", "completed", etc. para almacenar la información de cada tarea.
//esta libreria "jakarta.persistence" es la que nos permite usar anotaciones para mapear esta clase a una tabla en la base de datos, 
//lo que nos facilita la gestión de los datos de las tareas. 
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity //esta anotación indica que esta clase es una entidad que se mapeará a una tabla en la base de datos.
@Table (name = "todos") //esta anotación especifica el nombre de la tabla en la base de datos que se usará para almacenar las tareas, en este caso "todos".
@Getter //esta anotación de Lombok genera automáticamente los getters para todos los atributos de la clase
@Setter //esta anotación de Lombok genera automáticamente los setters para todos los atributos de la clase

public class Todo {

    @Id //empezamos con el atributo "id", que es el identificador único de cada tarea. Esta anotación indica que este atributo es la clave primaria de la tabla en la base de datos.
    @GeneratedValue(strategy=GenerationType.IDENTITY) //esta anotación indica que el valor de este atributo se generará automáticamente por la base de datos, utilizando una estrategia de generación de identidad.
     private Long id; //el tipo de dato es "Long" porque es un número entero grande que puede almacenar muchos valores, lo que es útil para el identificador de las tareas.
    

     @Column(nullable=false) //esta anotación indica que este atributo no puede ser nulo en la base de datos, lo que significa que cada tarea debe tener un título.
     private String title; //el tipo de dato es "String" porque es una cadena de texto que representa el título de la tarea.

     @Column(columnDefinition="TEXT") //esta anotación indica que este atributo se almacenará como un tipo de dato "TEXT" en la base de datos, lo que permite almacenar descripciones largas para las tareas.
     private String description; //el tipo de dato es "String" porque es una cadena de texto que representa la descripción de la tarea.


    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean completed;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    //jpa recomienda tener un constructor sin argumentos para poder crear instancias de la clase "Todo" sin necesidad de proporcionar valores para los atributos.
    public Todo() {
    }

      // Constructor con parámetros (útil para testing)
    public Todo(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed != null ? completed : false;
    }


    }
