package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {

	public DistroController() {
		super();
	}
	
	private String os () {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void chamaProcesso(String c) {
		try {
			Process processo = Runtime.getRuntime().exec(c);
			InputStream fluxo = processo.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains("PRETTY_NAME=")) {
					String l[] = linha.split("\"");
					System.out.println("Nome: " + l[1]);
				} else if (linha.contains("VERSION=")) {
					String l[] = linha.split("\"");
					System.out.println("Versão: " + l[1]);
				}
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} catch (Exception e) {
			String msg = e.getMessage();
			System.err.println(msg);
		}
		
	}
	
	public void exibeDistro() {
		String os = os();
		if (os.contains("Windows")) {
			System.out.println("Não se trata de um SO Linux");
		}else if (os.contains("Linux")) {
			String c = "cat /etc/os-release";
			chamaProcesso(c);
		
		}
	}

}
