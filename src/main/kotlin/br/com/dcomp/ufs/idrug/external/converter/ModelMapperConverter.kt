package br.com.dcomp.ufs.idrug.external.converter

import br.com.dcomp.ufs.idrug.api.converter.IdrugConverter
import br.com.dcomp.ufs.idrug.common.annotations.Converter
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration

@Converter
class ModelMapperConverter : IdrugConverter {
    private val mapper : ModelMapper = criarMapperParaObjetosImutaveis()

    private fun criarMapperParaObjetosImutaveis(): ModelMapper {

        val mapper = ModelMapper()
        mapper.configuration.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setFieldMatchingEnabled(true)
        return mapper
    }

    override fun <O, D> converterObjeto(objetoOrigem: O, classeDestino: Class<D>): D {
        return mapper.map(objetoOrigem, classeDestino)
    }

    override fun <O, D> converterLista(listOrigem: List<O>, classeDestino: Class<D>): List<D> {
        return listOrigem.map { converterObjeto(it, classeDestino) }.toList()
    }
}