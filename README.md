# Gestão de treinamento
## Descrição

Esta interface foi desenvolvida para uma empresa realizar um controle de participantes de um evento. A interface permite:

```
- O cadastro de pessoas, com nome e sobrenome;
- O cadastro das salas do evento, com nome e lotação;
- O cadastro dos espaços de café com lotação;
- A consulta de cada pessoa;
- A consulta de cada sala/espaço;
```

## Construção


A seguir é apresentada uma imagem da estrutura de solução criada para sustentar as funcionalidades descritas acima:

![Estrutura](https://i.ibb.co/qFH398f/java-grupo-2.png)

As principais camadas apresentadas na imagem possuem as seguintes funções:

```
-> Controllers: responsáveis por obter as informações de entrada, 
invocar as funcionalidades e retornar os resultados
-> Application: classe detentora do método main
-> Model: classes detentoras dos atributos empregues no código
-> Repositories: responsáveis pela construção HTML da solução
```

## Instalação

Para a construção deste programa, foi utilizado o software gratuito Spring Tools Suite 4 for Eclipse, disponível para download neste [link](https://spring.io/tools).

O banco de dados utilizado foi o PostgreSQL versão 9.6.21, disponível para download neste [link](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads). 
A senha padrão requerida para utilizar o banco de dados é 'admin'. Deve ser criado um database com o nome 'gestaotreinamento' para a utilização do código.

## Utilização

Para utilizar este desenvolvimento, basta seguir os passos de instalação descritos acima e executar o projeto na ferramenta escolhida, neste caso, o Spring Tools Suite.

Para acessar a interface, primeiramente o programa deve ser executado. Após a execução, o seguinte host deve ser informado no seu navegador:

```
localhost:8080
```
A interface será apresentada e estará pronta para ser utilizada.

## Contribuições

Os autores agradecem as opiniões e sugestões de melhoria que possam direcioná-los após utilizar a solução, ficando à disposição para contribuições futuras. 


## Autoria

Código desenvolvido pelos aspirantes à tecnologia Adriano Warmling, Jefferson Roberto Bublitz Junior, Lorran Pereira dos Santos, Nádia Vanzuita Hansen e Yuri Francis Piffer.

## Licença de utilização

Projeto open source, sem utilização de licenças ou restrições de uso.

## Status do projeto

Versão inicial 1.0 concluída e entregue em 15/03/2021.

