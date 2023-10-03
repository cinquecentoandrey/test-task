package com.cinqeucento.engineservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "comments_comment_id_gen")
    @GenericGenerator(
            name = "comments_comment_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "comments_comment_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "comment_id")
    private Long id;

    @NotEmpty(message = "The comment of the content must not be empty.")
    @Size(max = 128, message = "The comment of the content must be less than 128 symbols.")
    @Column(name = "comment_content")
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private TaskEntity task;

}
