import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class App {
    public static void main(String[] args) {
        String moedas = "USD-BRL,EUR-BRL";
        String url = "https://economia.awesomeapi.com.br/json/last/" + moedas;

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

            // Obtém as cotações das moedas
            double usd = json.getJSONObject("USDBRL").getDouble("bid");
            double eur = json.getJSONObject("EURBRL").getDouble("bid");
    
            // Exibe as cotações das moedas
            System.out.println("USD: " + usd);
            System.out.println("EUR: " + eur);
            
            // Converte 100 dólares em euros
            double valorEmDolares = 100;
            double valorEmEuros = valorEmDolares * usd / eur;
            System.out.println(valorEmDolares + " dólares equivalem a " + valorEmEuros + " euros");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
