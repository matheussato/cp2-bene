package br.com.fiap.asseguravel.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Table(name = "TB_IMOVEL",
uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NUM_REGIS_CARTORIO",
                columnNames = "NUM_REGIS_CART_IMOVEL"
        )
})
public class Imovel {

    @Id
    @GeneratedValue(generator = "SQ_IMOVEL",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_IMOVEL",sequenceName = "SQ_IMOVEL")

    @Column(name = "ID_IMOVEL")
    private long id;

    @Column(name = "QTD_QUARTOS_IMOVEL")
    private int qtdQuartos;

    @Column(name = "QTD_BANHEIROS_IMOVEL")
    private int qtdBanheiros;

    @Column(name = "QTD_VAGAS_GARAGEM_IMOVEL")
    private int intQtdVagasDeGaragem;

    @Column(name = "NUM_REGIS_CART_IMOVEL")
    private String numeroRegistroNoCartorio;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "TB_PROPRIETARIOS",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_DONO",
                            referencedColumnName = "ID_PESSOA",
                            foreignKey = @ForeignKey(name = "FK_DONO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_PROPRIETARIO",
                            referencedColumnName = "ID_PESSOA",
                            foreignKey = @ForeignKey(name = "FK_PROPRIETARIO")
                    )
            }
    )

    protected Set<Pessoa> proprietarios = new LinkedHashSet<>();


    public Imovel() {
    }

    public Imovel(long id, int qtdQuartos, int qtdBanheiros, int intQtdVagasDeGaragem, String numeroRegistroNoCartorio) {
        this.id = id;
        this.qtdQuartos = qtdQuartos;
        this.qtdBanheiros = qtdBanheiros;
        this.intQtdVagasDeGaragem = intQtdVagasDeGaragem;
        this.numeroRegistroNoCartorio = numeroRegistroNoCartorio;
    }


    public long getId() {
        return id;
    }

    public Imovel setId(long id) {
        this.id = id;
        return this;
    }

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public Imovel setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
        return this;
    }

    public int getQtdBanheiros() {
        return qtdBanheiros;
    }

    public Imovel setQtdBanheiros(int qtdBanheiros) {
        this.qtdBanheiros = qtdBanheiros;
        return this;
    }

    public int getIntQtdVagasDeGaragem() {
        return intQtdVagasDeGaragem;
    }

    public Imovel setIntQtdVagasDeGaragem(int intQtdVagasDeGaragem) {
        this.intQtdVagasDeGaragem = intQtdVagasDeGaragem;
        return this;
    }

    public String getNumeroRegistroNoCartorio() {
        return numeroRegistroNoCartorio;
    }

    public Imovel setNumeroRegistroNoCartorio(String numeroRegistroNoCartorio) {
        this.numeroRegistroNoCartorio = numeroRegistroNoCartorio;
        return this;
    }


    public Set<Pessoa> getProprietarios() {
        return Collections.unmodifiableSet(proprietarios);
    }


    public Imovel addProprietario(Pessoa pessoa) {
        this.proprietarios.add(pessoa);
        return this;
    }


    public Imovel removerProprietario(Pessoa pessoa) {
        this.proprietarios.remove(pessoa);
        return this;
    }


    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", qtdQuartos=" + qtdQuartos +
                ", qtdBanheiros=" + qtdBanheiros +
                ", intQtdVagasDeGaragem=" + intQtdVagasDeGaragem +
                ", numeroRegistroNoCartorio='" + numeroRegistroNoCartorio + '\'' +
                ", proprietarios=" + proprietarios +
                '}';
    }
}
