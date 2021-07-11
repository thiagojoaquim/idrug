package br.com.dcomp.ufs.idrug.api.models

import javax.validation.constraints.NotNull

data class DisponibilizarMedicamentoRequestDTO (
    @NotNull(message = "A quantidade é obrigatória.")
    val quantidade : Int
)