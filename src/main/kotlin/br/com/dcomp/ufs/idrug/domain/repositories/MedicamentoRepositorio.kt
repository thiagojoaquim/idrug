package br.com.dcomp.ufs.idrug.domain.repositories

import br.com.dcomp.ufs.idrug.domain.entities.Medicamento
import org.springframework.data.repository.PagingAndSortingRepository

interface MedicamentoRepositorio : PagingAndSortingRepository<Medicamento, Long> {
}