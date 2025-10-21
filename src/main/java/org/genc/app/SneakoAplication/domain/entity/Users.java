package org.genc.app.SneakoAplication.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.security.PrivateKey;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String userName;

    private String email;

    private String address;

    private Long phoneNumber;

    private Long password;


    @ManyToMany
    private Roles role;


    @Version
    private Long version;




}
