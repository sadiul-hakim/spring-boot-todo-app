package com.todo.todoApp.controller;

import com.todo.todoApp.entity.Todo;
import com.todo.todoApp.repository.TodoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    @GetMapping("/{todoId}")
    public Todo getATodo(@PathVariable Long todoId){
        return todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);
    }
}
