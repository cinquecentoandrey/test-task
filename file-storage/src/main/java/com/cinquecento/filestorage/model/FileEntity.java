package com.cinquecento.filestorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "data")
    private byte[] data;

    @Column(name = "type")
    private String type;

    @Column(name = "uploaded_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public FileEntity(String fileName, byte[] data, String type, Date uploadedAt, Date updatedAt) {
        this.fileName = fileName;
        this.data = data;
        this.type = type;
        this.uploadedAt = uploadedAt;
        this.updatedAt = updatedAt;
    }
}
