package parcial.proxy_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProxyController {

    public List<String> backendServices = Arrays.asList(
            System.getenv("SERVICE1_URL"),
            System.getenv("SERVICE2_URL")
    );

    int currentIndex = 0;

    public String getNextService(){
        String service = backendServices.get(currentIndex);
        currentIndex = (currentIndex + 1) % backendServices.size();
        System.out.println(service);
        return service;
    }

    @GetMapping("/lucasseq")
    public String proxyLucasSequence(@RequestParam("value") String value) throws IOException {
        String url = getNextService() + "/lucasseq?value=" + value;
        return proxyRequest(url);
    }
    public  String proxyRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        String res = "";
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            res = String.valueOf(response);
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return res;
    }
}
