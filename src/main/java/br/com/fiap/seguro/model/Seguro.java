package br.com.fiap.seguro.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TP_SEGURO")
@Table(name = "TB_SEGURO")
public abstract class Seguro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_SEGURO")
    @SequenceGenerator(name = "SQ_SEGURO",sequenceName = "SQ_SEGURO")

    @Column(name = "ID_SEGURO")
    private Long id;

    @Column(name = "INI_VIGEN_SEGURO")
    private LocalDate inicioVigencia;

    @Column(name = "FIM_VIGEN_SEGURO")
    private LocalDate fimVigencia;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CONTRATANTE",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_SEGURO_PESSOA")
    )
    private Pessoa contratante;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CORRETOR",
            referencedColumnName = "ID_CORRETOR",
            foreignKey = @ForeignKey(name = "FK_SEGURO_CORRETOR")
    )
    private Corretor corretor;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "TB_BENEFICIARIOS",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_CLIENTE",
                            referencedColumnName = "ID_PESSOA",
                            foreignKey = @ForeignKey(name = "FK_CLIENTE")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_BENEFICIARIO",
                            referencedColumnName = "ID_PESSOA",
                            foreignKey = @ForeignKey(name = "FK_BENEFICIARIO")
                    )
            }
    )
    private Set<Pessoa> beneficiarios = new LinkedHashSet<>();

    public Seguro() {
    }

    public Seguro(Long id, LocalDate inicioVigencia, LocalDate fimVigencia, Pessoa contratante, Corretor corretor) {
        this.id = id;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.contratante = contratante;
        this.corretor = corretor;
    }


    public Long getId() {
        return id;
    }

    public Seguro setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public Seguro setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
        return this;
    }

    public LocalDate getFimVigencia() {
        return fimVigencia;
    }

    public Seguro setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
        return this;
    }

    public Pessoa getContratante() {
        return contratante;
    }

    public Seguro setContratante(Pessoa contratante) {
        this.contratante = contratante;
        return this;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public Seguro setCorretor(Corretor corretor) {
        this.corretor = corretor;
        return this;
    }


    public Set<Pessoa> getBeneficiarios() {
        return Collections.unmodifiableSet(beneficiarios);
    }

    public Seguro addBeneficiario(Pessoa pessoa) {
        this.beneficiarios.add(pessoa);
        return this;
    }

    public Seguro removeBeneficiario(Pessoa pessoa) {
        this.beneficiarios.remove(pessoa);
        return this;
    }


    @Override
    public String toString() {
        return "Seguro{" +
                "id=" + id +
                ", inicioVigencia=" + inicioVigencia +
                ", fimVigencia=" + fimVigencia +
                ", contratante=" + contratante +
                ", corretor=" + corretor +
                ", beneficiarios=" + beneficiarios +
                '}';
    }
}
