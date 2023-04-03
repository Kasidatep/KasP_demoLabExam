package kasp.int204.demolabexam.repositories;

import kasp.int204.demolabexam.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    //custom query
    List<Todo> findTodoByStatusOrderByDateDesc(String status);
}

