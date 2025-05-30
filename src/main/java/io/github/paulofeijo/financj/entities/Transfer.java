package io.github.paulofeijo.financj.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Transfer extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @ManyToOne
    private Account accountDebit;

    @ManyToOne
    private Account accountCredit;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private Double amount;
}
