package co.com.autos.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
    private String nombreUsuario;

    @Column(name = "contraseña", nullable = false, length = 255)
    private String contraseña;

    @Column(name = "email", unique = true, length = 150)
    private String email;

    @Column(name = "rol", length = 20)
    private String rol = "ROLE_USER";

    @Column(name = "fecha_registro", updatable = false, insertable = false)
    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Auto> autos;


}
