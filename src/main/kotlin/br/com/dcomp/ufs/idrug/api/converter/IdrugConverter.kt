package br.com.dcomp.ufs.idrug.api.converter

interface IdrugConverter {

    fun <O, D> converterObjeto(objetoOrigem: O, classeDestino: Class<D>): D


    fun <O, D> converterLista(listOrigem: List<O>, classeDestino: Class<D>): List<D>

}
