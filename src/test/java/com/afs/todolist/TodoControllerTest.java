package com.afs.todolist;

import com.afs.todolist.entity.Todo;
import com.afs.todolist.repository.TodoRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    MockMvc client;

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    void cleanRepository() {
        todoRepository.deleteAll();
    }

    @Test
    void should_get_all_todos_when_perform_get_given_todos() throws Exception {
        //given
        String id = new ObjectId().toString();
        todoRepository.save(new Todo(id, "test1", false));

        //when & then
        client.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].text").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].done").value(false));
    }

    @Test
    void should_return_204_when_perform_delete_given_todos() throws Exception {
        //given
        String id = new ObjectId().toString();
        todoRepository.save(new Todo(id, "test1", false));

        //when
        client.perform(MockMvcRequestBuilders.delete("/todos/{id}" , id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        //then
        assertThat(todoRepository.findAll(), empty());
    }
    @Test
    void should_return_400_when_perform_delete_given_invalid_id_and_todos() throws Exception {
        //given & when
        client.perform(MockMvcRequestBuilders.delete("/todos/{id}" , "1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        //then
        assertThat(todoRepository.findAll(), empty());
    }
}
