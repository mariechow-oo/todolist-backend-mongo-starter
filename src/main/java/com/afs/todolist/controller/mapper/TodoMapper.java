package com.afs.todolist.controller.mapper;

import com.afs.todolist.controller.dto.TodoCreateRequest;
import com.afs.todolist.controller.dto.TodoUpdateRequest;
import com.afs.todolist.entity.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo toEntity(TodoCreateRequest request) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(request, todo);
        todo.setDone(false);
        return todo;
    }

    public Todo toEntity(TodoUpdateRequest request) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(request, todo);
        return todo;
    }
}
