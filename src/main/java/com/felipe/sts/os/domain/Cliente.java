package com.felipe.sts.os.domain;

public class Cliente extends Pessoa {
    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }
}
