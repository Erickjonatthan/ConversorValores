package br.com.empresa.dominio.modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ConversorMoeda {

    public double converte(String siglaOrigem, String siglaSaida) {
        String url = "https://economia.awesomeapi.com.br/json/last/" + siglaOrigem + "-" + siglaSaida;

        try {
            // Faz a requisição para a API
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Lê a resposta da API
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // Converte a resposta em um objeto JSON
            JSONObject json = new JSONObject(sb.toString());

            // Obtém a cotação da moeda
            double valorSaida = json.getJSONObject(siglaOrigem + siglaSaida).getDouble("bid");
            return  valorSaida;

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

}
