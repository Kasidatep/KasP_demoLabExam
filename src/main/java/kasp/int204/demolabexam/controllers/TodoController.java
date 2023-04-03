package kasp.int204.demolabexam.controllers;

import kasp.int204.demolabexam.dtos.PageDto;
import kasp.int204.demolabexam.dtos.TodoDto;
import kasp.int204.demolabexam.entities.Todo;
import kasp.int204.demolabexam.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("")
    public List<Todo>  getTodos(){
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public  Todo getTodoById(@PathVariable Integer id){
        return todoService.getTodoById(id);
    }

    @GetMapping("/status/{status}")
    public List<Todo> getTodosByStatus(@PathVariable String status){
        return todoService.getTodosByStatus(status);
    }

    @PostMapping("")
    public Todo addNewTodo(@RequestBody Todo todo){
        return todoService.addNewTodo(todo);
    }

    @PostMapping("/{id}")
    public Todo editTodo(@PathVariable Integer id, @RequestBody Todo todo){
        return todoService.editTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Integer id){
        todoService.deleteTodo(id);
    }

    //DTO
    @GetMapping("/dto/{id}")
    public TodoDto getTodosPageDto(@PathVariable Integer id){
        return todoService.getTodosDtoById(id);
    }

    @GetMapping("/page")
    public Page<Todo> getTodosPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size, @RequestParam(defaultValue = "date") String sort){
        return todoService.getTodosPage(page,size,sort);
    }

    @GetMapping("/dto")
    public List<TodoDto> getTodosPageDto(){
        return  todoService.getTodosDto();
    }

    @GetMapping("/dto-page")
    public PageDto<TodoDto> getTodosPageDto(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size, @RequestParam(defaultValue = "date") String sort){
        return  todoService.getTodosDtoPage(page, size, sort);
    }

    @GetMapping("/maplist-dto")
    public List<TodoDto> getTodosDtoWithMapList(){
        return  todoService.getTodosDtoWithMapList();
    }
}
