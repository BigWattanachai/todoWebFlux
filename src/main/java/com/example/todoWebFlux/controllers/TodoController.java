package com.example.todoWebFlux.controllers;

import com.example.todoWebFlux.domain.Todo;
import com.example.todoWebFlux.repositories.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public Flux<Todo> allTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Todo>
    getTodo(@PathVariable String id) {
        return todoRepository.findById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<?>> saveTodo(@RequestBody Mono<Todo> todoMono) {
        return todoMono.flatMap(todo -> todoRepository.save(todo).map(saved ->
                ResponseEntity.created(URI.create(String.format("/todos/%s", saved.getId()))).body(saved)));

    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTodo(@PathVariable String id) {
        return todoRepository.deleteById(id);
    }
}
