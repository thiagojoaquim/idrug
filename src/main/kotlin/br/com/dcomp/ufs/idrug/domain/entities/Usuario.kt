package br.com.dcomp.ufs.idrug.domain.entities

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    @NotBlank(message = "O nome é obrigatório.")
    var nome: String,
    @Column(nullable = false)
    @NotBlank(message = "A senha é obrigatória.")
    var senha: String,
    @Column(nullable = false, unique = true)
    @NotBlank(message = "O email é obrigatório.")
    var email: String,
    @ManyToOne
    @JoinColumn(name = "profile_id")
    var perfil: Perfil? = null
)