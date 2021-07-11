package br.com.dcomp.ufs.idrug.api.usecases.impl

import br.com.dcomp.ufs.idrug.common.annotations.UseCase
import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoDisponibilidade
import br.com.dcomp.ufs.idrug.domain.exceptions.NotFoundException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import br.com.dcomp.ufs.idrug.domain.repositories.FarmaciaRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.MedicamentoDisponibilidadeRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.MedicamentoRepositorio
import br.com.dcomp.ufs.idrug.domain.usecases.DisponibilizarMedicamentoUseCase
import java.time.LocalDate

@UseCase
class DisponibilizarMedicamentoImpl(
    private val farmaciaRepositorio: FarmaciaRepositorio,
    private val medicamentoRepositorio: MedicamentoRepositorio,
    private val medicamentoDisponibilidadeRepositorio: MedicamentoDisponibilidadeRepositorio
):DisponibilizarMedicamentoUseCase {

    override fun disponibilizar(idFarmacia: Long, idMedicamento: Long, quantidade : Int): MedicamentoDisponibilidade {
        val farmacia = farmaciaRepositorio.findById(idFarmacia)
        if (farmacia.isEmpty)
            throw NotFoundException(Erro.FARMACIA_NAO_ENCONTRADA)

        val medicamento = medicamentoRepositorio.findById(idMedicamento)
        if (medicamento.isEmpty)
            throw NotFoundException(Erro.MEDICAMENTO_NAO_ENCONTRADO)

        val medicamentoDisponibilizado = medicamentoDisponibilidadeRepositorio.findByMedicamentoIdAndFarmaciaId(
            medicamentoId = idMedicamento,
            farmaciaId = idFarmacia
        )

        if (medicamentoDisponibilizado.isEmpty){
            val medicamentoParaDisponibilizar = MedicamentoDisponibilidade(medicamento = medicamento.get(), farmacia = farmacia.get(), quantidade = quantidade)
            return medicamentoDisponibilidadeRepositorio.save(medicamentoParaDisponibilizar)
        }

        medicamentoDisponibilizado.get().quantidade += quantidade
        return medicamentoDisponibilidadeRepositorio.save(medicamentoDisponibilizado.get())

    }
}