package src;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ConsultaDivisa {

    public JsonObject response;

    public double consultarCambio(String divisaDestino){
        Map<String, JsonElement> conversionRates = response.get("conversion_rates").getAsJsonObject().asMap();
        return Double.parseDouble(String.valueOf(conversionRates.get(divisaDestino)));
    }

    public void actualizarResponse(String divisaOrigen) throws MalformedURLException {
        String url_str = "https://v6.exchangerate-api.com/v6/1f7073ebdd578e85eee2566f/latest/"+divisaOrigen;
        URL url = new URL(url_str);
        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            response = jsonobj.getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
