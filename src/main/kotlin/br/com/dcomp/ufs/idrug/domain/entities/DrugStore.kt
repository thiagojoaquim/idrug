package br.com.dcomp.ufs.idrug.domain.entities

import org.hibernate.validator.constraints.br.CNPJ
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class DrugStore(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(nullable = false, unique = true)
    @CNPJ
    var cnpj: String,
    @ManyToOne
    @JoinColumn(name = "profile_id")
    var profile: Profile,
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User
)