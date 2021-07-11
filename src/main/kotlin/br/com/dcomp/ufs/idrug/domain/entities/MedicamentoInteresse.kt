package br.com.dcomp.ufs.idrug.domain.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class MedicamentoInteresse (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    @ManyToOne
    var paciente: Paciente,
    @ManyToOne
    var medicamento: Medicamento,
    var dataRegistro : LocalDateTime
)