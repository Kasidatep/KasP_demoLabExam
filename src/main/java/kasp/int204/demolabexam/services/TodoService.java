package kasp.int204.demolabexam.services;

import kasp.int204.demolabexam.dtos.PageDto;
import kasp.int204.demolabexam.dtos.TodoDto;
import kasp.int204.demolabexam.entities.Todo;
import kasp.int204.demolabexam.repositories.TodoRepository;
import kasp.int204.demolabexam.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    public Todo getTodoById(Integer id){
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find todo id="+id));
    }

    public List<Todo> getTodosByStatus(String status){
        return todoRepository.findTodoByStatusOrderByDateDesc(status);
    }

    public Todo addNewTodo(Todo todo) {
        if(todoRepository.existsById(todo.getId())){
            throw new RuntimeException("this Id already exists!!");
        }else{
            return todoRepository.saveAndFlush(todo);
        }
    }

    public Todo editTodo(Integer id, Todo todo) {
        if(todoRepository.existsById(id)){
            Todo todo1 = getTodoById(id);
            todo1.setDate(todo.getDate());
            todo1.setTitle(todo.getTitle());
            todo1.setStatus(todo.getStatus());
            todoRepository.saveAndFlush(todo1);
            return todo1;
        }else{
            throw  new RuntimeException("Id "+id +" is not exists!!");
        }
    }

    public void deleteTodo(Integer id) {
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("cannot find todo id="+id);
        }
    }

    public Page<Todo> getTodosPage(Integer page, Integer size, String sort) {
        Sort sortObj = Sort.by(sort);
        Pageable pageable = PageRequest.of(page,size,sortObj);
        return todoRepository.findAll(pageable);
    }

    public List<TodoDto> getTodosDto() {
        List<Todo> todos = getTodos();
        return todos.stream().map(t -> modelMapper.map(t, TodoDto.class)).collect(Collectors.toList());
    }

    public TodoDto getTodosDtoById(Integer id) {
        return modelMapper.map(getTodoById(id), TodoDto.class);
    }

    public List<TodoDto> getTodosDtoWithMapList() {
        return listMapper.mapList(getTodos(), TodoDto.class, modelMapper);
    }

    public PageDto<TodoDto> getTodosDtoPage(Integer page, Integer size, String sort) {
        return listMapper.toPageDTO(getTodosPage(page, size, sort), TodoDto.class, modelMapper);
    }
}
