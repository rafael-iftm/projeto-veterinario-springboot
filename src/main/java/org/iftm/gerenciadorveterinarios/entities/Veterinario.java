package org.iftm.gerenciadorveterinarios.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "tb_veterinario")
public class Veterinario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String especialidade;
    private BigDecimal salario;
    //private Instant dataContratacao;
}
