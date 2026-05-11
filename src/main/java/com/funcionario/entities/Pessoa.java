package com.funcionario.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "pessoa")
    private List<Funcionario> funcionarios;
}
