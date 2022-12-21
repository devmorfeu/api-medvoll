package br.com.medvoll.entity.user;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "usuarios")
@Entity(name = "Usuario")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String login;

    private String password;
}