package com.example.nttdata.models;

import com.example.nttdata.util.enums.TypeMovementEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = Movement.MovementBuilder.class)
@Entity
@Table(name = "t_movements")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "mov_date", nullable = false)
    private Date movementDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "mov_type", nullable = false)
    private TypeMovementEnum typeMovement;

    @Column(name = "mov_value", nullable = false)
    private Double movementValue;

    @Column(name = "mov_balance", nullable = false)
    private Double movementBalance;

    @Column(name = "mov_state", nullable = false)
    private Boolean state;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "mov_act_id", referencedColumnName = "act_id")
    private Account account;

    public Movement() {

    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class MovementBuilder{

    }
}
