package com.bit.nc4_final_project.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class TourApiExplorer {
    @Value("${mzen.secretKey}")
    private String secretKey;

    public String getAllContent() {
        StringBuilder allContent = new StringBuilder();
        int totalPages = getTotalPages();
        int numOfRowsPerPage = 200;

        try {
            for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
                String pageContent = getContentForPage(pageNo, numOfRowsPerPage);
                allContent.append(pageContent);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return allContent.toString();
    }

    public String getContentForPage(int pageNo, int numOfRowsPerPage) {
        StringBuilder sb = new StringBuilder();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/areaBasedList1");
            urlBuilder.append("?").append(URLEncoder.encode("serviceKey", "UTF-8")).append("=").append("8ZcskwShapZjaTsOEAyi%2FH6j66Q98M0c2q%2BJ6DHxWAqNqLw5KPO1dYEp8XVIYJ8YHXaRq4VDMjcCWK6N0Qn8lw%3D%3D");
            urlBuilder.append("&").append(URLEncoder.encode("_type", "UTF-8")).append("=").append(URLEncoder.encode("json", "UTF-8"));
            urlBuilder.append("&").append(URLEncoder.encode("MobileOS", "UTF-8")).append("=").append(URLEncoder.encode("ETC", "UTF-8"));
            urlBuilder.append("&").append(URLEncoder.encode("MobileApp", "UTF-8")).append("=").append(URLEncoder.encode("AppTest", "UTF-8"));
            urlBuilder.append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(numOfRowsPerPage), "UTF-8"));
            urlBuilder.append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(pageNo), "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public int getTotalPages() {
        int totalPages = 0;
        try {
            String firstPageContent = getContentForPage(1);
            JSONObject jsonObject = new JSONObject(firstPageContent);
            JSONObject bodyObject = jsonObject.getJSONObject("response").getJSONObject("body");
            int totalCount = bodyObject.getInt("totalCount");
            int numOfRows = bodyObject.getInt("numOfRows");

            totalPages = (int) Math.ceil((double) totalCount / numOfRows);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return totalPages;
    }
}