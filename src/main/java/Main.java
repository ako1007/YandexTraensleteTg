import com.google.gson.Gson;
import model.Leng;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        String url="https://dictionary.yandex.net/api/v1/dicservice.json/getLangs?key=";
        String key="dict.1.1.20220822T121549Z.6ec532e029a78f9c.06955ca564a0315e54a7dfaf83328adbba083a4a";
        HttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url+key);
        HttpResponse execute = httpClient.execute(httpGet);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(execute.getEntity().getContent()));
        Gson gson=new Gson();
        String[] strings = gson.fromJson(bufferedReader, String[].class);
        int tr=1;
        for (String string : strings) {
            System.out.println(tr+": "+string);
            tr++;
        }
        System.out.println("Qaysi tildan qaysi tilga");
        int chooseLang=scanner.nextInt();
        String string = strings[chooseLang - 1];


        System.out.println("Suz kiriting: ");
        String word=scanner.next();
        httpClient=HttpClients.createDefault();
        httpGet=new HttpGet("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key="+key+"&lang="+string+"&text="+word);
        execute=httpClient.execute(httpGet);
        bufferedReader=new BufferedReader(new InputStreamReader(execute.getEntity().getContent()));
        Leng leng = gson.fromJson(bufferedReader, Leng.class);
        System.out.println(leng.getDef()[0].getText()+""+leng.getDef()[0].getTr()[0].getText());
    }
}
