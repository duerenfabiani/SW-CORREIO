
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="compra")
public class Compra implements Serializable {

    @Id
    @SequenceGenerator(name="seq_compra", sequenceName="seq_compra_id", allocationSize=1)
    @GeneratedValue(generator="seq_compra", strategy= GenerationType.SEQUENCE)
    private Integer id;    
    @Column(name="nome",length=50,nullable=true)
    private String nome;   
    @Column(name="endereco",length=50,nullable=true)
    private String endereco;   
    @Column(name="valor",length=20,nullable=true)
    private Float valor;    
    @Column(name="cep_origem",length=8,nullable=false)
    private String cepOrigem;    
    @Column(name="cep_destino",length=8,nullable=false)
    private String cepDestino;    
    @Column(name="frete",length=20,nullable=true)
    private String frete;    
    @Column(name="prazo",length=20,nullable=true)
    private String prazo;    
    @Column(name="servico",length=10,nullable=false)
    private String servico;    
    @Column(name="altura",length=10,nullable=false)
    private BigDecimal altura;    
    @Column(name="largura",length=10,nullable=false)
    private BigDecimal largura;    
    @Column(name="comprimento",length=10,nullable=false)
    private BigDecimal comprimento;    
    @Column(name="peso",length=10,nullable=false)
    private BigDecimal peso;    
    @Column(name="formato",length=1,nullable=false)
    private int formato;    
    @Column(name="diametro",length=10,nullable=true)
    private BigDecimal diametro;
    
    public Compra() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getCepOrigem() {
        return cepOrigem;
    }

    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = cepOrigem;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public String getFrete() {
        return frete;
    }

    public void setFrete(String frete) {
        this.frete = frete;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public BigDecimal getLargura() {
        return largura;
    }

    public void setLargura(BigDecimal largura) {
        this.largura = largura;
    }

    public BigDecimal getComprimento() {
        return comprimento;
    }

    public void setComprimento(BigDecimal comprimento) {
        this.comprimento = comprimento;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public int getFormato() {
        return formato;
    }

    public void setFormato(int formato) {
        this.formato = formato;
    }

    public BigDecimal getDiametro() {
        return diametro;
    }

    public void setDiametro(BigDecimal diametro) {
        this.diametro = diametro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comprinha{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", valor=" + valor + ", cepOrigem=" + cepOrigem + ", cepDestino=" + cepDestino + ", frete=" + frete + ", prazo=" + prazo + ", servico=" + servico + ", altura=" + altura + ", largura=" + largura + ", comprimento=" + comprimento + ", peso=" + peso + ", formato=" + formato + ", diametro=" + diametro + '}';
    }
}
