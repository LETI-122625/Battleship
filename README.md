# Battleship

Basic academic version of Battleship game to build upon.


Número de Aluno, Nome 
* 122625, Afonso Mexia
* 122618, Tiago Melo
* 122605, Rodrigo Vilhena



⚙️ Integração Contínua (CI)

Este projeto utiliza GitHub Actions para automatizar o processo de build e testes unitários, suportando o processo de Integração Contínua.

Workflow de Testes
O workflow é configurado para ser desencadeado automaticamente nas seguintes situações:

A cada push para o ramo principal (main).

A cada pull request (PR) submetido para fusão no ramo principal.

Tecnologia: Java com Apache Maven.

Ação: O workflow executa o comando mvn -B package, que é responsável por compilar o código e, crucialmente, executar automaticamente todos os testes unitários JUnit 6 definidos no projeto.

O estado da última execução do workflow pode ser verificado na secção Actions do repositório.