package com.afs.todolist.service;

import com.afs.todolist.entity.Todo;
import com.afs.todolist.exception.InvalidIdException;
import com.afs.todolist.exception.TodoNotFoundException;
import com.afs.todolist.repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    private void validateObjectId(String id){
        if(!ObjectId.isValid(id)){
            throw new InvalidIdException(id);
        }
    }

    public Todo add(Todo todo) { return todoRepository.save(todo); }

    public Todo update(String id, Todo todo) {
        validateObjectId(id);
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
        existingTodo.setDone(todo.getDone());
        existingTodo.setText(todo.getText());
        return todoRepository.save(existingTodo);
    }

    public void delete(String id) {
        validateObjectId(id);
        todoRepository.deleteById(id);
    }
}
