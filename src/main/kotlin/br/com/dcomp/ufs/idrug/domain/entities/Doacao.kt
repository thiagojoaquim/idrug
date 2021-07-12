package br.com.dcomp.ufs.idrug.domain.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Doacao (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var dataDoacao: LocalDateTime,
    @ManyToOne
    var medicamentoInteresse: MedicamentoInteresse,
    @ManyToOne
    var medicamentoDisponibilidade: MedicamentoDisponibilidade,
    var concluida : Boolean
)