package br.com.dcomp.ufs.idrug.domain.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Medicamento (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var nome : String,
    var dosagem : Long,
    var tarja : String,
    var unidadeDeMedida : String
)