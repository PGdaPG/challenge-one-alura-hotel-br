package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hospede {
    Integer idHospede;
    String nome;
    String sobreNome;
    Date dataNascimento; 
    String nacionalidade;
    String telefone;
    int numeroReserva;

    public Hospede(String nome, String sobreNome, Date dataNascimento, String nacionalidade, String telefone,
            int numeroReserva) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.numeroReserva = numeroReserva;
    }

    public Hospede(int idHospede, String nome, String sobreNome, Date dataNascimento, String nacionalidade, String telefone,
            int numeroReserva) {
        this.idHospede = idHospede;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.numeroReserva = numeroReserva;  
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobreNome() {
        return sobreNome;
    }
    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public int getNumeroReserva() {
        return numeroReserva;
    }
    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }
    
    public String getDataNascimentoFormatada() {
        SimpleDateFormat padrao = new SimpleDateFormat("yyyy-MM-dd");
        return padrao.format(this.dataNascimento);
    }

    public int getIdHospede() {
        return this.idHospede;
    }

    
}
