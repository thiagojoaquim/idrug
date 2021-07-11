package br.com.dcomp.ufs.idrug.domain.entities

import javax.persistence.*

@Entity
class MedicamentoDisponibilidade(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    var medicamento: Medicamento,
    @ManyToOne
    @JoinColumn(name = "farmacia_id")
    var farmacia: Farmacia,
    var quantidade: Int
)