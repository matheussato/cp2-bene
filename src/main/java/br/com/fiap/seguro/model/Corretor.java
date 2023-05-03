package br.com.fiap.seguro.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_CORRETOR",
uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NUM_SUSEP_CORRETOR",
                columnNames = "NUM_SUSEP_CORRETOR"
        )
})
public class Corretor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_CORRETOR")
    @SequenceGenerator(name = "SQ_CORRETOR",sequenceName = "SQ_CORRETOR")

    @Column(name = "ID_CORRETOR")
    private long id;

    @Column(name = "NUM_SUSEP_CORRETOR")
    private String numeroSUSEP;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_CORRETOR_PESSOA")
    )
    private Pessoa pessoa;


    public Corretor() {
    }

    public Corretor(long id, String numeroSUSEP, Pessoa pessoa) {
        this.id = id;
        this.numeroSUSEP = numeroSUSEP;
        this.pessoa = pessoa;
    }

    public long getId() {
        return id;
    }

    public Corretor setId(long id) {
        this.id = id;
        return this;
    }

    public String getNumeroSUSEP() {
        return numeroSUSEP;
    }

    public Corretor setNumeroSUSEP(String numeroSUSEP) {
        this.numeroSUSEP = numeroSUSEP;
        return this;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Corretor setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    @Override
    public String toString() {
        return "Corretor{" +
                "id=" + id +
                ", numeroSUSEP='" + numeroSUSEP + '\'' +
                ", pessoa=" + pessoa +
                '}';
    }
}
