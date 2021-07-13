# Idrug - Backend



## Sobre o Projeto Idrug

O Idrug tem o objetivo de, como qualquer aplicação na Web, conectar pessoas neste caso específico conectar as farmácias que possuem medicamentos perto do vencimento à pacientes que necessitam de um tratamento específico, a custo zero e que resida próximo a alguma das farmácias cadastrada no aplicativo. Dessa forma, o aplicativo ajuda tanto no âmbito social quanto no ambiental além de reduzir os gastos das farmácias com o descarte correto.


### Delimitação do Problema
As farmácias que possuem medicamentos perto do vencimento devem executar processos definidos pela Anvisa para descarte. O aplicativo fornecerá a opção de disponibilizar esses remédios perto do vencimento(1 a 3 meses) e a custo zero  para pacientes que necessitam do tratamento de um determinado remédio, com base na localização do paciente e farmácia o aplicativo conectará os dois usuários. O objetivo é oferecer esses remédios a custo zero e ao mesmo tempo ajudar as farmácias a reduzir os gastos com o descarte desses medicamentos.

## Sobre o Backend

### Linguagens e dependências usadas

- [Kotlin](https://kotlinlang.org/docs/home.html)
- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Model Mapper](http://modelmapper.org/getting-started/)
- [PostgreSQL](https://www.postgresql.org/)
- [Maven](https://maven.apache.org/)
- [Heroku](https://www.heroku.com/about)

### Arquitetura

Para a arquitetura, seguimos os conceitos apresentados por Robert Martin no livro Arquitetura Limpa. Na figura abaixo, temos as camadas utilizadas no projeto.

![image](https://user-images.githubusercontent.com/42274127/125374629-ca464a80-e35d-11eb-842d-b292bc12181c.png)

Entretanto, decidimos seguir alguns conceitos de modelagem de domínio proposto por Eric Evans em Domain Driven Design. Portanto, a nossa arquitetura contem as seguintes camadas:

- **external:** Essa é a camada mais externa, a ideia é que nenhuma outra camada acesse ela, pois ela implementa regras externas como o acesso ao banco de dados, utilizações de frameworks e integrações.
- **api:** Nesta camada é implementada toda a regra da aplicação, como, por exemplo, os controllers e a própria implementação dos casos de uso.
- **domain:** Nesta camada esta presente toda regra do negócio. A ideia é que todas as outras camadas dependam somente desta de domínio. Importante característica é que os casos de uso são sempre interfaces, se mantendo no campo abstrato e delegando para as camadas mais acima a implementação. 

#### Regras da Arquitetura
Como a arquitetura foi pensada com base nos conceitos apresentados no livro Arquitetura Limpa, os princípios do SOLID são o coração da implementação. Portanto, abaixo está uma série de regras definidas para a implementação.
- **Dependência deve ser das camadas mais externas para as mais externas:** Como apresentado na arquitetura limpa, a arquitetura deve depender somente ao que realmente interessa, o domínio. Portanto, a camada de domínio não deve conter dependências com outras camadas, evitando o acoplamento das regras de negócio com as regras da aplicação ou de frameworks.
- **As camadas se comunicam através de Interfaces:** O princípio "D" do SOLID diz que classes com fortes dependências devem depender de abstração e não implementação. Portanto, todo e qualquer comunicação entre camadas, é feita através de interfaces, possibilitando o princípio "O" do SOLID, onde uma classe deve ser aberta para extenção e fechada para modificação.
- **Ninguém deve depender da camada mais externa:** Assim como a camada mais interna não deve depender de ninguém, ninguém deve depender da classe mais externa, pois nela reside os frameworks, conexões com banco de dados e outras ferramentas, o que leva a uma maior volatilidade. 
