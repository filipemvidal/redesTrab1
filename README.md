# Servidor e Cliente Echo - Redes de Computadores

Este projeto implementa um servidor Multi-threaded e um cliente TCP em Java utilizando Sockets. A aplicação foi desenvolvida como parte da atividade da disciplina de Redes de Computadores e inclui um protocolo de comunicação simples por linha de comando.

## Funcionalidades

- **Servidor Multi-threaded:** Capaz de aceitar e gerenciar múltiplas conexões de clientes simultaneamente, exibindo o IP e a porta de cada nova conexão.
- **Protocolo de Comunicação:**
  - `echo <mensagem>`: O servidor recebe a string após o comando e a devolve para o cliente.
  - `quit`: Encerra a conexão do cliente com o servidor.

## Pré-requisitos

- Java Development Kit (JDK) 21.
- Apache Maven.
- Sistema Linux.

## Estrutura do Projeto

- `EchoServer.java`: Classe principal do servidor, gerencia a porta e aceita novas conexões.
- `ThreadCliente.java`: classe auxiliar do servidor, responsável por tratar a comunicação isolada com cada cliente.
- `EchoClient.java`: Classe executável do cliente que realiza a conexão e envia comandos.

## Compilação

Para compilar o projeto e gerar o arquivo `.jar` executável, abra o terminal na pasta raiz do projeto (onde está o arquivo `pom.xml`) e execute:

```bash
mvn clean package
```

Este comando utilizará o Maven para compilar o código e empacotar tudo no arquivo target/trab1-1.0-SNAPSHOT-jar-with-dependencies.jar.

## Execução
Após a compilação bem-sucedida, você precisará de pelo menos dois terminais abertos: um para rodar o servidor e outro para rodar o cliente.

### 1. Iniciando o Servidor
O servidor deve ser iniciado primeiro. Como ele foi definido como a classe principal (Main-Class) no pom.xml, você pode executá-lo diretamente pelo .jar:

```bash
java -jar target/trab1-1.0-SNAPSHOT-jar-with-dependencies.jar
```

O servidor ficará em execução aguardando conexões na porta 4444.

### 2. Iniciando o Cliente
Em um novo terminal, inicie o cliente. É necessário usar a flag -cp (classpath) para apontar para a classe do cliente, já que o .jar aponta para o servidor por padrão. O programa exige dois argumentos: <nome> e <host>.

```bash
java -cp target/trab1-1.0-SNAPSHOT-jar-with-dependencies.jar redes.trab1.EchoClient NOME localhost
```

## Exemplos de Uso
No terminal do cliente, digite os comandos do protocolo:

Comando *echo*:

```plaintext
echo testando a comunicação
[NOME] testando a comunicação
```

Comando *quit*:

```plaintext
quit
Closing connection to localhost
```