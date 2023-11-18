package cl.duoc.api.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name ="id_usr")
    private int id_usr;

    @Basic
    @Column(name="nombre")
    private String nombre;

    @Basic
    @Column(name="apellido")
    private String apellido;

    @Basic
    @Column(name="user")
    private String user;

    @Basic
    @Column(name="password")
    private String password;

    @Basic
    @Column(name="activo")
    private int activo;
}
