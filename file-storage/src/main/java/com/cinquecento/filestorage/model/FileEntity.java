package com.cinquecento.filestorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "files_file_id_gen")
    @GenericGenerator(
                        name = "files_file_id_gen",
                        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                        parameters = {
                                @Parameter(name = "sequence_name", value = "files_file_id_seq"),
                                @Parameter(name = "initial_value", value = "1"),
                                @Parameter(name = "increment_size", value = "1")
                        }
    )
    @Column(name = "file_id")
    private Long id;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    private String type;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @PrePersist
    public void onUpload() {
        this.uploadedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PostUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public FileEntity(String fileName, String path, String type) {
        this.fileName = fileName;
        this.path = path;
        this.type = type;
    }
}
