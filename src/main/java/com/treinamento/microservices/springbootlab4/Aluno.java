package com.treinamento.microservices.springbootlab4;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aluno implements Serializable {

    private static final long serialVersionUID = 6848208103049780959L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nome;
    Integer matricula;
    @Column(unique = true)
    String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Temporal(TemporalType.TIMESTAMP)
    Date dataNascimento;

}
