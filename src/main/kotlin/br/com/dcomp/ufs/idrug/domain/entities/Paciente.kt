package br.com.dcomp.ufs.idrug.domain.entities

import br.com.dcomp.ufs.idrug.domain.validators.Cpf
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.persistence.*

@Entity
class Paciente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    var dataNascimento : LocalDate,
    @Column(nullable = false, unique = true)
    @CPF(message = "O CPF é inválido.")
    var cpf : String,
    @OneToOne(cascade = [CascadeType.ALL])
    var usuario: Usuario
)