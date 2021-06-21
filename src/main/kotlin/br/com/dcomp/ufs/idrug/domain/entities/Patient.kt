package br.com.dcomp.ufs.idrug.domain.entities

import br.com.dcomp.ufs.idrug.domain.validators.Cpf
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long,
    var birthDate : LocalDate,
    @Column(nullable = false, unique = true)
    @Cpf
    var cpf : String,
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User
)