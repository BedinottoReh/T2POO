# Trabalho 02 de Programação Orientada a Objetos

Grupo: Renata Bedinotto e Sarah Severo.

Sistema de Alocação de Grade Horária. Este projeto é uma ferramenta desenvolvida em Java para apoiar o cruzamento entre disciplinas, professores e horários.

## Tecnologias Utilizadas
Java Swing e CardLayout para interface gráfica.
FlatLaf para o design.
Gson para persistência de dados local em formato JSON.

## Estrutura:
```text
src/
├── exception/
│   ├── CompetenciaInvalida.java
│   └── ConflitoHorario.java
├── model/
│   ├── Alocacao.java
│   ├── Disciplina.java
│   ├── Horario.java
│   └── Professor.java
├── repository/
│   └── Repositorio.java
├── service/
│   ├── ConflitoHorarioExcecao.java
│   └── GradeManager.java
├── strategy/
│   ├── RegraValidacao.java
│   ├── ValidadorCargaHorariaDisciplina.java
│   ├── ValidadorChoqueHorario.java
│   └── ValidadorCompetencia.java
├── view/
│   └── MainFrame.java
└── Main.java
```

## Arquitetura do Sistema
Camada de Modelo ('model')
- Professor: representa o docente, guarda o nome e a área de conhecimento.
- Disciplina: guarda os dados da disciplina(código, nome, área) e sua carga horária (30h, 60h ou 90h).
- Horario: classe que mapeia o tempo de aula usando enums para 'DiaSemana', 'Turno' e 'Bloco' (primeiro ou seguindo horário).
- Alocacao: classe associativa que consolida o match entre 'Professor', 'Disciplina' e 'Horario'.

Camada de Serviço('service')
- GradeManager: Gerencia a lista de alocações ativas na memória e dispara as regras de validação.

Camada de Persistência('repository')
-Repositorio: responsável por salvar e carregar os dados das listas em arquivos '.json'.

Camada de regras de négocio('strategy')
O sistema utiliza o padrão de projeto Strategy através da interface `RegraValidacao`. Cada validação é uma classe isolada:
- ValidadorCompetencia: Barra a alocacao se a área da disciplina não bater com as competências do professor.
- ValidadorChoqueHorario: Impede que um professor ocupe o mesmo dia, turno e bloco em mais de uma matéria ao mesmo tempo.
- ValidadorCargaHorariaDisciplina: Garante que a disciplina não extrapole o limite máximo de períodos semanais permitidos por sua carga horária (30h = 1 bloco, 60h = 2 blocos, 90h = 3 blocos).

Camada de Visão (`view`)
MainFrame: Gerencia a navegação por painéis em camadas (*CardLayout*), renderiza a matriz da grade horária usando tags HTML e captura exceções para exibir alertas amigáveis (`JOptionPane`) ao usuário.

## Contribuições 
- Renata Bedinotto: Desenvolvimento do front-end, renderização dinâmica de tabelas, tratamento defensivo de erros na interface e rotinas do CRUD de edição de cadastros.
- Sarah Severo: Modelagem do motor de negócios, configuração da persistência de arquivos JSON via Gson e arquitetura polimórfica do padrão Strategy para as regras de validação.

##Como executar o projeto
Como o projeto utiliza bibliotecas externas (`.jar`) para o design e persistência de dados, siga os comandos abaixo no terminal para compilar e rodar o sistema corretamente.

### Pré-requisitos
* Possuir o **Java JDK** instalado na máquina (versão 17 ou superior).

### Passo a Passo

1. Abra o terminal na pasta raiz do projeto (`T2POO`).
2. Execute o comando abaixo para **compilar** todos os pacotes do sistema:
   ```bash
   javac -cp "lib/gson-2.10.1.jar:lib/flatlaf-3.5.1.jar" $(find src -name "*.java")

Com o projeto compilado, execute o comando abaixo para iniciar a interface gráfica:
   ```bash
   java -cp "lib/gson-2.10.1.jar:lib/flatlaf-3.5.1.jar:src" Main
