package br.com.dcomp.ufs.idrug.external.converter

import br.com.dcomp.ufs.idrug.api.models.FarmaciaDTO
import br.com.dcomp.ufs.idrug.api.models.RegistroFarmaciaRequestDTO
import br.com.dcomp.ufs.idrug.domain.entities.Farmacia
import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import org.modelmapper.spi.MappingContext

class FarmaciaConverter {


    class PacienteConverter {

        class ToFarmaciaDTO: org.modelmapper.Converter<Farmacia, FarmaciaDTO> {
            override fun convert(p0: MappingContext<Farmacia, FarmaciaDTO>?): FarmaciaDTO {
                val fonte = p0!!.source
                return FarmaciaDTO(fonte.usuario.nome, fonte.cnpj, fonte.usuario.email, fonte.usuario.perfil!!.id)
            }
        }

        class ToFarmacia: org.modelmapper.Converter<RegistroFarmaciaRequestDTO, Farmacia> {
            override fun convert(p0: MappingContext<RegistroFarmaciaRequestDTO, Farmacia>?): Farmacia {
                val fonte = p0!!.source
                val usuario = Usuario(null, fonte.nome, fonte.senha, fonte.email, null)
                return Farmacia(null, fonte.cnpj, usuario)
            }
        }
    }
}