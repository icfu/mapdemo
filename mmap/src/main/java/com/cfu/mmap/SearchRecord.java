package com.cfu.mmap;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "search_record")
public class SearchRecord implements Serializable {
    private static final long serialVersionUID = 8862194356280461843L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String userId;

    @Column
    private String searchValue;

    @Column
    private int count;

    @CreatedDate
    @Column
    private Date createdDate;

    @CreatedBy
    @Column
    private String createdBy;

    @LastModifiedDate
    @Column
    private Date updatedDate;

    @LastModifiedBy
    @Column
    private String updatedBy;
}
