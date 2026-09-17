package redes.trab1;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        int port = 4444; 
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        while (true) {
            // Espera blocante até alguma requisição de conexão
            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from " + clientSocket.getRemoteSocketAddress());

            // Cria e inicia uma nova thread para o cliente conectado
            new ClientThread(clientSocket).start();
        }
    }
}

// Classe que estende Thread para tratar cada cliente simultaneamente
class ClientThread extends Thread {
    private Socket clientSocket;

    public ClientThread(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Cria as "streams" para o socket (buffer)
            // In in = new In(clientSocket);
            // Out out = new Out(clientSocket);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String s;

            // Espera a leitura do dado e implementa a máquina de estados do protocolo
            while ((s = in.readLine()) != null) { 
                // Comando quit encerra a conexão
                if (s.equals("quit")) { 
                    break; // Sai do loop para fechar os sockets
                } 
                // Comando echo possui um parâmetro (a mensagem)
                else if (s.startsWith("echo ")) { 
                    // Pega tudo após "echo " (índice 5 em diante) e devolve ao cliente
                    out.println(s.substring(5)); 
                } 
                else {
                    out.println("Comando inválido. Use 'echo <msg>' ou 'quit'.");
                }
            }

            // Fecha a conexão (e o socket) do cliente específico
            System.err.println("Closing connection with client");
            out.close();
            in.close();
            clientSocket.close();
            
        } catch (Exception e) {
            System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
        }
    }
}