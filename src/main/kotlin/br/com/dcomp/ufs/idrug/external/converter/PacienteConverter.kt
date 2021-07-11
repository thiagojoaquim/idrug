package br.com.dcomp.ufs.idrug.external.converter

import br.com.dcomp.ufs.idrug.api.models.RegistrarPacienteRequestDTO
import br.com.dcomp.ufs.idrug.domain.entities.Paciente
import br.com.dcomp.ufs.idrug.domain.entities.Usuario
import org.modelmapper.spi.MappingContext

class PacienteConverter {

    class ToPacienteDTO: org.modelmapper.Converter<Paciente, RegistrarPacienteRequestDTO> {
        override fun convert(p0: MappingContext<Paciente, RegistrarPacienteRequestDTO>?): RegistrarPacienteRequestDTO {
            val fonte = p0!!.source
            return RegistrarPacienteRequestDTO(fonte.usuario.nome, fonte.dataNascimento, fonte.cpf, fonte.usuario.email, fonte.usuario.senha)
        }
    }

    class ToPaciente : org.modelmapper.Converter<RegistrarPacienteRequestDTO, Paciente> {
        override fun convert(p0: MappingContext<RegistrarPacienteRequestDTO, Paciente>?): Paciente {
            val fonte = p0!!.source
            val usuario = Usuario(null, fonte.nome, fonte.senha, fonte.email, null)
            return Paciente(null, fonte.dataNascimento, fonte.cpf, usuario)
        }
    }
}