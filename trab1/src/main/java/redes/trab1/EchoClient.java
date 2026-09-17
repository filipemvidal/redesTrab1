package redes.trab1;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        // Verifica se os argumentos foram passados corretamente
        if (args.length < 2) {
            System.err.println("Uso: java -cp target/... redes.trab1.EchoClient <nome> <host>");
            return;
        }

        String screenName = args[0];
        String host = args[1];
        int port = 4444;

        // Conecta ao servidor e abre os streams
        Socket socket = new Socket(host, port);
        
        // Leitor da entrada padrão (teclado) nativo do Java
        Scanner stdin = new Scanner(System.in);
        
        // Leitor e escritor do socket usando as classes nativas do Java
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // true para auto-flush
        
        System.err.println("Connected to " + host + " on port " + port);
        System.out.println("Digite um comando ('echo <mensagem>' ou 'quit'):");

        // Lê da entrada padrão stdin, envia, escreve resposta
        while (stdin.hasNextLine()) {
            // Leitura do que você digitou no terminal
            String s = stdin.nextLine();
            
            // Envio pelo socket (enviamos APENAS o texto exato para o protocolo funcionar, sem o screenName)
            out.println(s);
            
            // Se o comando for quit, encerra o laço do lado do cliente também
            if (s.equals("quit")) {
                break;
            }
            
            // Pega a resposta do servidor e imprime na tela
            String resposta = in.readLine();
            if (resposta == null) {
                System.err.println("Server disconnected.");
                break; // Servidor fechou a conexão
            }
            System.out.println("[" + screenName + "] " + resposta);
        }

        // Encerra os sockets
        System.err.println("Closing connection to " + host);
        out.close();
        in.close();
        stdin.close();
        socket.close();
    }
}