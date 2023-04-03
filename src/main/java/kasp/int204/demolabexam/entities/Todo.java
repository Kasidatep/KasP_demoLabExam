package kasp.int204.demolabexam.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "todolist")
public class Todo {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String title;

    @Column(name = "completionDate")
    private String date;

    @Column(nullable = false)
    private String status;
}
