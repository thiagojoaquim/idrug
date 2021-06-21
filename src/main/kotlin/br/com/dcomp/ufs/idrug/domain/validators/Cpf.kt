package br.com.dcomp.ufs.idrug.domain.validators



import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import javax.validation.Constraint

@Constraint(validatedBy = [CpfValidator::class] )
@Target(ElementType.FIELD, ElementType.PARAMETER)
@Retention(value = RetentionPolicy.RUNTIME)
annotation class Cpf()
