package br.com.dcomp.ufs.idrug.external.converter

import br.com.dcomp.ufs.idrug.api.models.AutenticarResponseDTO
import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import org.modelmapper.Converter
import org.modelmapper.spi.MappingContext

class UsuarioConverter {
    class ToUsuarioResponseDTO : Converter<Usuario, AutenticarResponseDTO> {
        override fun convert(p0: MappingContext<Usuario, AutenticarResponseDTO>?): AutenticarResponseDTO {
            val fonte = p0!!.source
            return AutenticarResponseDTO(fonte.id!!, fonte.perfil!!.id )
        }

    }
}