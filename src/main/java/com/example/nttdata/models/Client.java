package com.example.nttdata.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Client extends Person{

    @Column(name = "cli_username", unique = true, nullable = false)
    @NotNull(message = "El usuario es requerido")
    private String username;

    @Column(name = "cli_password", nullable = false)
    @NotNull(message = "La contrasenia es requerida")
    private String password;

    @Column(name = "cli_state")
    private Boolean state;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Account> accounts;

    public Client() {
    }
}
