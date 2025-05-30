package io.github.paulofeijo.financj.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.paulofeijo.financj.enums.Type;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Entry extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Category category;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private Double amount;

    private Type type;
}
