package br.com.dcomp.ufs.idrug.domain.validators

import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CpfValidator : ConstraintValidator<Cpf, String> {

    override fun initialize(constraintAnnotation: Cpf?) {
    }

    override fun isValid(cpf: String?, p1: ConstraintValidatorContext?): Boolean {
        if (cpf == null || cpf.isEmpty() || cpf.isBlank())
            return false
        return isCpf(cpf)
    }

    private fun isCpf(cpf: String): Boolean {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999" ||
            cpf.length != 11
        ) return false
        val dig10: Char
        val dig11: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int


        return try {
            sm = 0
            peso = 10
            i = 0
            while (i < 9) {
                num = (cpf[i].toInt() - 48)
                sm = sm + num * peso
                peso = peso - 1
                i++
            }
            r = 11 - sm % 11
            dig10 = if (r == 10 || r == 11) '0' else (r + 48).toChar() // converte no respectivo caractere numerico
            sm = 0
            peso = 11
            i = 0
            while (i < 10) {
                num = (cpf[i].toInt() - 48)
                sm = sm + num * peso
                peso = peso - 1
                i++
            }
            r = 11 - sm % 11
            dig11 = if (r == 10 || r == 11) '0' else (r + 48).toChar()

            if (dig10 == cpf[9] && dig11 == cpf[10]) true else false
        } catch (erro: InputMismatchException) {
            false
        }
    }
}