package study2.translator;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Translator2 {

    public static void main(String[] args) throws Exception {
        // HTTP 클라이언트 생성
        HttpClient client = HttpClient.newHttpClient();

        // 번역할 텍스트
        String textToTranslate = "Hello, world!";
        
        // 요청 데이터 설정
        Map<String, String> requestData = new HashMap<>();
        requestData.put("q", textToTranslate);
        requestData.put("source", "en");
        requestData.put("target", "ko");
        requestData.put("format", "text");

        // JSON으로 변환
        Gson gson = new Gson();
        String json = gson.toJson(requestData);

        // HTTP 요청 생성
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://libretranslate.com/translate"))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(json))
            .build();

        // HTTP 응답 받기
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // 응답 출력
        System.out.println("Translated Text: " + response.body());
    }
}
