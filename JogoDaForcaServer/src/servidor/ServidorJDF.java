/**
 * @author RA00184036
 * @version 22/04/2018
 */

package servidor;

import java.io.*;
import java.net.*;
import java.util.Vector;
import logger.ILog;
import logger.LogTexto;


public class ServidorJDF {

	public static void main(String args[])
	{
		try {
			// criando um socket que fica escutando a porta 2222.
			providerSocket = new ServerSocket(porta);
			clientes = new Vector<PrintStream>();
			logger.escreverLog("---------Servidor Iniciado-----------");
			// Loop principal.
			while (true) {
				System.out.println("Esperando alguem se conectar...");
				logger.escreverLog("Eperando alguem se conectar...");
				Socket conexao = providerSocket.accept();
				System.out.println("conectado");
				new Thread(new TratadorConexoes(conexao)).start();
			}
		}
		catch (IOException e) {
			logger.escreverLog("IOException: " + e);
		}
	}

	private static ServerSocket providerSocket;
	private static ILog logger = new LogTexto();
	private static final int porta = 2222;
	private static Vector<PrintStream> clientes;

	private static class TratadorConexoes implements Runnable{

		public TratadorConexoes(Socket conexao) {
			this.conexao = conexao;
		}
	
		private Socket conexao = null;

		private BufferedReader in;
		private PrintStream out;
		private String nomeCliente;

		@Override
		public void run() {
			try{

				System.out.println("Connection received from " + conexao.getInetAddress().getHostName());
				// Atribui input e output streams
				in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
				out = new PrintStream(conexao.getOutputStream());
				
				nomeCliente = in.readLine();
				System.out.println(nomeCliente);
				if (nomeCliente == null) {return;}
				
				clientes.add(out);
				
				String index = in.readLine(); 
				System.out.println(index);
				while (!Thread.currentThread().isInterrupted()) {
					
					//enviar e receber mensagems do cliente
					
					if(index.equals("null")) {
						throw new IOException();
					}
					
					// espera por uma nova linha.
					index = in.readLine();
				}
				
				clientes.remove(out);
				conexao.close();

			}
			catch(Exception ioException){
				System.out.println("erro input ou output");
			}
			finally{
				// Fecha a conexao com o servidor
				try{
					conexao.close();
					System.out.println("Conexao terminada com sucesso");
					logger.escreverLog("Conexao terminada com sucesso");
				}
				catch(IOException ioException){
					System.out.println("Conexao terminada com erro");
					logger.escreverLog("Erro ao fechar conexao com o servidor");
				}
			}

		}

		//funcao para enviar mensagems para o cliente
		void sendMessage(PrintStream out, String msg) throws IOException {
			out.println(msg);
		}

	}
}
