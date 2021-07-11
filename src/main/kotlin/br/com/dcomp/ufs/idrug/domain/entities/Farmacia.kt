package br.com.dcomp.ufs.idrug.domain.entities

import org.hibernate.validator.constraints.br.CNPJ
import javax.persistence.*

@Entity
class Farmacia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    @CNPJ
    var cnpj: String,
    @OneToOne(cascade = [CascadeType.ALL])
    var usuario: Usuario
)