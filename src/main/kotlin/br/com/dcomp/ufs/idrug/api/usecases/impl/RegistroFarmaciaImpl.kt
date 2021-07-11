package br.com.dcomp.ufs.idrug.api.usecases.impl

import br.com.dcomp.ufs.idrug.common.SHA256Converter
import br.com.dcomp.ufs.idrug.common.annotations.UseCase
import br.com.dcomp.ufs.idrug.domain.entities.Farmacia
import br.com.dcomp.ufs.idrug.domain.entities.Perfil
import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import br.com.dcomp.ufs.idrug.domain.exceptions.BadRequestException
import br.com.dcomp.ufs.idrug.domain.messages.Erro
import br.com.dcomp.ufs.idrug.domain.repositories.FarmaciaRepositorio
import br.com.dcomp.ufs.idrug.domain.repositories.UsuarioRepositorio
import br.com.dcomp.ufs.idrug.domain.usecases.RegistroFarmaciaUseCase

@UseCase
class RegistroFarmaciaImpl(private val farmaciaRepositorio: FarmaciaRepositorio, val usuarioRepositorio: UsuarioRepositorio) : RegistroFarmaciaUseCase {
    override fun registrar(farmacia: Farmacia): Farmacia {
        var farmaciaBase = farmaciaRepositorio.findByCnpj(farmacia.cnpj)
        if (farmaciaBase.isPresent) {
            throw BadRequestException(Erro.CNPJ_JA_REGISTRADO)
        }
        var userWithThisEmail = usuarioRepositorio.findByEmail(farmacia.usuario.email)
        if (userWithThisEmail.isPresent) {
            throw BadRequestException(Erro.EMAIL_JA_REGISTRADO)
        }
        farmacia.usuario.perfil = Perfil(id = 2)
        farmacia.usuario.senha = SHA256Converter.convert(farmacia.usuario.senha)
        return farmaciaRepositorio.save(farmacia)
    }
}