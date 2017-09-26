package br.com.cuiateam.ajudame.ong;

/**
 * Created by Marcos on 20/09/2017.
 */

public class OngItem {
    public String id;
    public String nome;
    public String descricao;
    public String trabalhos;
    public String favorecidos;
    public String endereco;
    public String localizacao;

    public OngItem() {
        this.id = "";
        this.nome = "";
        this.descricao = "";
        this.trabalhos = "";
        this.favorecidos = "";
        this.endereco = "";
        this.localizacao = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTrabalhos() {
        return trabalhos;
    }

    public void setTrabalhos(String trabalhos) {
        this.trabalhos = trabalhos;
    }

    public String getFavorecidos() {
        return favorecidos;
    }

    public void setFavorecidos(String favorecidos) {
        this.favorecidos = favorecidos;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
        public String toString() {
            return nome;
        }
}
