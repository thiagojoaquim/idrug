package br.com.dcomp.ufs.idrug.domain.listeners

import br.com.dcomp.ufs.idrug.domain.entities.Doacao
import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoDisponibilidade
import br.com.dcomp.ufs.idrug.domain.entities.MedicamentoInteresse
import br.com.dcomp.ufs.idrug.domain.repositories.DoacaoRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.MedicamentoDisponibilidadeRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.MedicamentoInteresseRepositorio
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class VerificarDoacaoListener(
    private val doacaoRepositorio: DoacaoRepositorio,
    private val medicamentoInteresseRepositorio: MedicamentoInteresseRepositorio,
    private val medicamentoDisponibilidadeRepositorio: MedicamentoDisponibilidadeRepositorio
) {

    @EventListener
    fun aoInserirInteresse(medicmentoInteresse: MedicamentoInteresse) {
        val disponibilidade = medicamentoDisponibilidadeRepositorio.findByMedicamentoId(medicmentoInteresse.medicamento.id)
        if (disponibilidade.isPresent && disponibilidade.get().quantidade > 0) {
            val doacao = Doacao(null, LocalDateTime.now(), medicmentoInteresse, disponibilidade.get(), false)
            doacaoRepositorio.save(doacao)
            disponibilidade.get().quantidade--
            medicamentoDisponibilidadeRepositorio.save(disponibilidade.get())
            medicamentoInteresseRepositorio.delete(medicmentoInteresse)
        }
    }

    @EventListener
    fun aoInserirDisponibilidade(medicamentoDisponibilidade: MedicamentoDisponibilidade) {
        val interesse = medicamentoInteresseRepositorio.findByMedicamentoId(medicamentoDisponibilidade.medicamento.id)
        if(interesse.isPresent) {
            val doacao = Doacao(null, LocalDateTime.now(), interesse.get(), medicamentoDisponibilidade, false)
            doacaoRepositorio.save(doacao)
            medicamentoDisponibilidade.quantidade--
            medicamentoDisponibilidadeRepositorio.save(medicamentoDisponibilidade)
            medicamentoInteresseRepositorio.delete(interesse.get())
        }
    }
}