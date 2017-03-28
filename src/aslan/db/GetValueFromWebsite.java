package aslan.db;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.jsoup.nodes.Document;
//@author javanshir

public class GetValueFromWebsite {

    static String url = "http://trackinn.az/GeoLoc/reports.aspx?login=ho&password=ho";
    static Document doc;

    public static void main(String[] args) throws MalformedURLException, IOException, Exception {
//        sendPost();
        //sayti save ela sourcedan datalari gotur sayti sil
        //parolcun dialog ac (db da parol table ac) forgot olanda emailina gondarsin parolu dayishmaycunda old, new txt yeri qoy
        //document.getElementById('maintable').outerHTML

//        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        //************
//        URLConnection connection;
//        connection = new URL(url).openConnection();
//        ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());
//        FileOutputStream fos = new FileOutputStream("/Users/javanshirmammadov/Desktop/information.html");
//        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        //************
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        webClient.waitForBackgroundJavaScript(50000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        // Create web request
        WebRequest requestSettings = new WebRequest(new URL(url), HttpMethod.POST);

        // Then we set the request parameters
//        requestSettings.setRequestParameters(new ArrayList());
//        requestSettings.getRequestParameters().add(new NameValuePair("maintable", "table"));

// Finally, we can get the page
        HtmlPage page = webClient.getPage(requestSettings);

        ScriptResult result = page.executeJavaScript("document.getElementById('maintable').outerHTML");
        System.out.println(result.toString());
        final HtmlTable table = page.getHtmlElementById("maintable");
        System.out.println(table.toString());
        table.getRows().stream().map((row) -> {
            System.out.println("Found row");
            return row;
        }).forEach((row) -> {
            row.getCells().stream().forEach((cell) -> {
                System.out.println("   Found cell: " + cell.asText());
            });
        });
    }

    public static void sendPost() throws Exception {

        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(MessageFormat.format("{0}\n", inputLine));
            }
        }

        //print result
        System.out.println(response.toString());

    }
}
