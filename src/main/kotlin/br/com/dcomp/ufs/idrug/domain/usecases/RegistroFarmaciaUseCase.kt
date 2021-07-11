package br.com.dcomp.ufs.idrug.domain.usecases

import br.com.dcomp.ufs.idrug.domain.entities.Farmacia

interface RegistroFarmaciaUseCase {
    fun registrar(farmacia: Farmacia) : Farmacia
}