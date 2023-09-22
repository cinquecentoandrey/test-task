package com.cinquecento.filestorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntityDTO {

    private String fileName;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] data;

    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
