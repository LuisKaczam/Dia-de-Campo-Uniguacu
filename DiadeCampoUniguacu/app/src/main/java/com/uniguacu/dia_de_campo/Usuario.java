package com.uniguacu.dia_de_campo;

public class Usuario implements Comparable<Usuario>{
    private String id, nome;
    private int pnts, foto;

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Usuario(String id, String nome, int pnts, int foto) {
        this.id = id;
        this.nome = nome;
        this.pnts = pnts;
        this.foto = foto;
    }

    public Usuario() {
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

    public int getPnts() {
        return pnts;
    }

    public void setPnts(int pnts) {
        this.pnts = pnts;
    }

    @Override
    public int compareTo(Usuario usuarioPnts) {
        return usuarioPnts.getPnts() - this.pnts;
    }
}