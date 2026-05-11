package com.funcionario.entities;

import com.funcionario.enums.Turno;
import jakarta.persistence.*;
import lombok.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4, nullable = false)
    private String matricula;

    @Column(nullable = false)
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
}

