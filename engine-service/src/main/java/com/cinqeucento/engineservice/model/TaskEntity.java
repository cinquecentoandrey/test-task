package com.cinqeucento.engineservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import com.cinqucento.engineservicestarter.model.CurrentTaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tasks_task_id_gen")
    @GenericGenerator(
            name = "tasks_task_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "tasks_task_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "task_id")
    private Long id;

    @NotEmpty(message = "The task name must not be empty.")
    @Size(max = 32, message = "The task name must be less than 128 symbols.")
    @Column(name = "task_name")
    private String taskName;

    @NotEmpty(message = "The task description must not be empty.")
    @Size(max = 512, message = "The task description must be less than 512 symbols.")
    @Column(name = "task_description")
    private String taskDescription;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "task_status")
    private CurrentTaskStatus taskStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_status_update")
    private LocalDateTime lastStatusUpdate;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.lastStatusUpdate = LocalDateTime.now();
        this.taskStatus = CurrentTaskStatus.OPEN;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private ProjectEntity project;

    @OneToMany(mappedBy = "task")
    @Cascade({org.hibernate.annotations.CascadeType.DELETE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CommentEntity> comments;

}
