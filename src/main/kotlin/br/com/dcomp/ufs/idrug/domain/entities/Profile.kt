package br.com.dcomp.ufs.idrug.domain.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
class Profile (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long,
    @NotBlank(message = "O nome deve ser preenchido")
    var name : String,
    @NotBlank(message = "O nome deve ser preenchido")
    var description: String
)