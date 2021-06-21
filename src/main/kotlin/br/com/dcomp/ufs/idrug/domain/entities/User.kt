package br.com.dcomp.ufs.idrug.domain.entities

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(nullable = false)
    @NotBlank(message = "O nome é obrigatório.")
    var name: String,
    @Column(nullable = false)
    @NotBlank(message = "A senha é obrigatória.")
    var password: String,
    @Column(nullable = false, unique = true)
    @NotBlank(message = "O email é obrigatório.")
    var email: String,
    @ManyToOne
    @JoinColumn(name = "role_id")
    var profile: Profile
)