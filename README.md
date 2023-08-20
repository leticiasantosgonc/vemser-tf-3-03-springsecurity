# <p align="center"> DBCursos Tech - Equipe 02 </p> 

<p align="center">
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white"/>
<img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
</p>

DBCursos Tech é um projeto em Java. O projeto foi um desafio proposto como teste do conhecimento obtido ao final do módulo-03 com Spring Data, Banco de Dados com JPA e Spring Security do programa Vem Ser, da empresa DBC Company.


## Funcionalidades

### Professor
- `GET/{idProfessor}`: procura o professor pelo id;
- `PUT/{idProfessor}`: atualiza um professor;
- `DELETE/{idProfessor}`: deleta um professor;
- `GET/professor`: lista todos os professores;
- `POST/professor`: cria um novo professor;
- `GET/professor/paginado`: lista professores páginado.

### Endereco
- `GET/{idEndereco}`: procura o endereco pelo id;
- `PUT/{idEndereco}`: atualiza um endereco;
- `DELETE/{idEndereco}`: deleta um endereco;
- `GET/endereco`: lista todos os enderecos;
- `POST/endereco`: cria um novo professor, vinculando ao aluno;
- `GET/endereco/relatorio`: lista relatório aluno com endereços.

### Curso
- `GET/{idCurso}`: procura o curso pelo id;
- `PUT/{idCurso}`: atualiza um curso;
- `DELETE/{idCurso}`: deleta um curso;
- `GET/curso`: lista todos os cursos;
- `POST/curso`: cria um novo curso, vinculando ao professor;
- `GET/curso/relatorio`: lista relatório de professores com os cursos.

### Aluno
- `GET/{idAluno}`: procura o aluno pelo id;
- `PUT/{idAluno}`: atualiza um aluno;
- `DELETE/{idAluno}`: deleta um aluno;
- `GET/aluno`: lista todos os alunos;
- `POST/aluno`: cria um novo aluno;
- `GET/aluno/paginado`: lista alunos páginado.

### Auth
- `POST/acesso`: gerar token de acesso;
- `POST/acesso/cadastrar`: cadastrar um novo usuário;

## Pré-requisitos
Para executar o projeto, siga os passos abaixo:

- Certifique-se de ter o Java Development Kit (JDK versão 17.0.7) instalado em sua máquina.
- Faça o download ou clone este repositório para o seu computador.
- Abra o projeto em sua IDE de preferência.
- O sistema irá apresentar um menu de opções para realizar as operações disponíveis.

## Contribuição
Desenvolvido pelos colaboradores:
- Breno Santos ([GitHub](https://github.com/breno-ms))
- Kamila Santos ([GitHub](https://github.com/kamilasst))
- Letícia Santos ([GitHub](https://github.com/leticiasantosgonc))
- Renan Bilhan ([GitHub](https://github.com/RenanBilhan))
