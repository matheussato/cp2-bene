package br.com.fiap.asseguravel.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;


@Entity
@Table(name = "TB_VEICULO",
uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_CHASSI_VEICULO",
                columnNames = "CHASSIS_VEICULO"
        ),
        @UniqueConstraint(
                name = "UK_PLACA_VEICULO",
                columnNames = "PLACA_VEICULO"
        )
})

public class Veiculo {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_VEICULO")
@SequenceGenerator(name = "SQ_VEICULO",sequenceName = "SQ_VEICULO")

    @Column(name = "ID_VEICULO")
    private long id;

    @Column(name = "CHASSIS_VEICULO")
    private String chassis;

    @Column(name = "PLACA_VEICULO")
    private String placa;

    @Column(name = "MODELO_VEICULO")
    private String modelo;

    @Column(name = "ANO_FABRIC_VEICULO")
    private int anoDeFabricacao;

    @Column(name = "FABRICANTE_VEICULO")
    private String fabricante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PROPRIETARIO",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_VEICULO_PESSOA")
    )
    private Pessoa proprietario;

    public Veiculo() {
    }

    public Veiculo(long id, String chassis, String placa, String modelo, int anoDeFabricacao, String fabricante, Pessoa proprietario) {
        this.id = id;
        this.chassis = chassis;
        this.placa = placa;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.fabricante = fabricante;
        this.proprietario = proprietario;
    }

    public long getId() {
        return id;
    }

    public Veiculo setId(long id) {
        this.id = id;
        return this;
    }

    public String getChassis() {
        return chassis;
    }

    public Veiculo setChassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public String getPlaca() {
        return placa;
    }

    public Veiculo setPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public String getModelo() {
        return modelo;
    }

    public Veiculo setModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public Veiculo setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
        return this;
    }

    public String getFabricante() {
        return fabricante;
    }

    public Veiculo setFabricante(String fabricante) {
        this.fabricante = fabricante;
        return this;
    }


    public Pessoa getProprietario() {
        return proprietario;
    }

    public Veiculo setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
        return this;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", chassis='" + chassis + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anoDeFabricacao=" + anoDeFabricacao +
                ", fabricante='" + fabricante + '\'' +
                ", proprietario=" + proprietario +
                '}';
    }
}
