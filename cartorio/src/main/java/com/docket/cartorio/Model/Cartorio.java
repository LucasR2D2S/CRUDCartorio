package com.docket.cartorio.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "cartorios")
public class Cartorio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    public Cartorio(){

    }

    public Cartorio(String nome){
        this.nome = nome;
    }

    public void setID(long id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public long getId(){
        return id;
    }

    @Column(name = "nome", nullable = false)
    public String getNome(){
        return nome;
    }

}


