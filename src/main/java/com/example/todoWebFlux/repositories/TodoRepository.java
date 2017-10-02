package com.example.todoWebFlux.repositories;

import com.example.todoWebFlux.domain.Todo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TodoRepository extends ReactiveCrudRepository<Todo, String> {
}
