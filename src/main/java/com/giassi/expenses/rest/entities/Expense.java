package com.giassi.expenses.rest.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="expenses")
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Basic
    private java.time.LocalDateTime creationDt;

    @Column(name="voice")
    private String voice;

    @Column(name="price", nullable = false)
    private double price;

    @OneToOne(optional = false)
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

}
