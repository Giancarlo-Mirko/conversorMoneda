import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConvertidorDeMoneda {
    public RespuestaApi obtenerDatosConversion(){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/2e9a64396a41e8d8a3344df2/latest/USD");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), RespuestaApi.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontre esa DIVISA");
        }

    }

    public double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) throws IOException {
        RespuestaApi respuestaAPI = obtenerDatosConversion();
        double tasaMonedaOrigen = respuestaAPI.conversion_rates().getOrDefault(monedaOrigen, 1.0);
        double tasaMonedaDestino = respuestaAPI.conversion_rates().getOrDefault(monedaDestino, 1.0);
        return (cantidad / tasaMonedaOrigen) * tasaMonedaDestino;
    }
    public boolean validarMoneda(String monedaNombre) throws IOException {
        RespuestaApi respuestaAPI = obtenerDatosConversion();
        return respuestaAPI.conversion_rates().containsKey(monedaNombre);
    }



}
