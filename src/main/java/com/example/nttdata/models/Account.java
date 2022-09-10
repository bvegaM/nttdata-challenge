package com.example.nttdata.models;

import com.example.nttdata.util.enums.TypeAccountEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = Account.AccountBuilder.class)
@Entity
@Table(name = "t_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "act_num", unique = true, nullable = false)
    private String numberAccount;
    @Column(name = "act_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeAccountEnum typeAccount;
    @Column(name = "act_balance", nullable = false)
    private Double balance;
    @Column(name = "act_state")
    private Boolean state;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
    @JoinColumn(name = "act_cli_id", referencedColumnName = "cli_id")
    private Client client;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Movement> movements;

    public Account() {
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountBuilder{

    }
}
