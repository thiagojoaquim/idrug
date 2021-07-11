package br.com.dcomp.ufs.idrug.api.usecases.impl

import br.com.dcomp.ufs.idrug.common.annotations.UseCase
import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoDisponibilidade
import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoInteresse
import br.com.dcomp.ufs.idrug.domain.exceptions.BadRequestException
import br.com.dcomp.ufs.idrug.domain.exceptions.NotFoundException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import br.com.dcomp.ufs.idrug.domain.repositories.MedicamentoInteresseRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.MedicamentoRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.PacienteRepositorio
import br.com.dcomp.ufs.idrug.domain.usecases.RegistrarInteresseUseCase
import java.time.LocalDateTime

@UseCase
class RegistroInteresseImpl(
    private val pacienteRepositorio: PacienteRepositorio,
    private val medicamentoRepositorio: MedicamentoRepositorio,
    private val medicamentoInteresseRepositorio: MedicamentoInteresseRepositorio
): RegistrarInteresseUseCase {

    override fun registrar(pacienteId : Long, medicamentoId : Long): MedicamentoInteresse {

        val paciente = pacienteRepositorio.findById(pacienteId)
        if (paciente.isEmpty)
            throw NotFoundException(Erro.PACIENTE_NAO_ENCONTRADO)

        val medicamento = medicamentoRepositorio.findById(medicamentoId)
        if (medicamento.isEmpty)
            throw NotFoundException(Erro.MEDICAMENTO_NAO_ENCONTRADO)

        val interesse = medicamentoInteresseRepositorio.findByMedicamentoIdAndPacienteId(medicamentoId = medicamentoId, pacienteId = pacienteId)

        if (interesse.isEmpty) {
            val medicamentoInteresse = MedicamentoInteresse(paciente = paciente.get(), medicamento = medicamento.get(), dataRegistro = LocalDateTime.now())
            return medicamentoInteresseRepositorio.save(medicamentoInteresse)
        }
        throw BadRequestException(Erro.INTERESSE_JA_CADASTRADO)
    }
}