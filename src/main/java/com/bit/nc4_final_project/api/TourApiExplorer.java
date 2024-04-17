package com.bit.nc4_final_project.api;

import com.bit.nc4_final_project.document.travel.AreaCode;
import com.bit.nc4_final_project.document.travel.SigunguCode;
import com.bit.nc4_final_project.document.travel.Travel;
import com.bit.nc4_final_project.document.travel.TravelDetail;
import com.bit.nc4_final_project.factory.travel.TravelDetailFactory;
import com.bit.nc4_final_project.factory.travel.impl.TravelDetail12Factory;
import com.bit.nc4_final_project.factory.travel.impl.TravelDetail14Factory;
import com.bit.nc4_final_project.factory.travel.impl.TravelDetail15Factory;
import com.bit.nc4_final_project.factory.travel.impl.TravelDetail28Factory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TourApiExplorer {
    @Value("${mzen.secretKey}")
    private String secretKey;

    private String sendHttpRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        StringBuilder response = new StringBuilder();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
        }
        conn.disconnect();
        return response.toString();
    }

    private String buildUrl(String path, String[] queryParams) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/");
        urlBuilder.append(path);
        return appendQueryParams(urlBuilder, queryParams).toString();
    }

    private StringBuilder appendQueryParams(StringBuilder urlBuilder, String[] queryParams) throws UnsupportedEncodingException {
        urlBuilder.append("?");
        for (String param : queryParams) {
            urlBuilder.append(param).append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        urlBuilder.append("&")
                .append(URLEncoder.encode("serviceKey", "UTF-8")).append("=").append(secretKey)
                .append("&").append(URLEncoder.encode("_type", "UTF-8")).append("=").append(URLEncoder.encode("json", "UTF-8"))
                .append("&").append(URLEncoder.encode("MobileOS", "UTF-8")).append("=").append(URLEncoder.encode("ETC", "UTF-8"))
                .append("&").append(URLEncoder.encode("MobileApp", "UTF-8")).append("=").append(URLEncoder.encode("AppTest", "UTF-8"))
                .append("&").append(URLEncoder.encode("contentTypeId", "UTF-8")).append("=").append(URLEncoder.encode("28", "UTF-8"));
        return urlBuilder;
    }

    public List<Travel> getList(int pageNo, int numOfRowsPerPage, int totalCnt) {
        List<Travel> travels = new ArrayList<>();
        int totalCount = (pageNo - 1) * numOfRowsPerPage;

        try {
            String url = buildUrl("areaBasedList1", new String[]{
                    "numOfRows=" + URLEncoder.encode(String.valueOf(numOfRowsPerPage), "UTF-8"),
                    "pageNo=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8")
            });
            String response = sendHttpRequest(url);

            JSONObject responseBody = new JSONObject(response).getJSONObject("response").getJSONObject("body");
            JSONArray itemsArray = responseBody.optJSONObject("items").optJSONArray("item");

            if (itemsArray != null) {
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject itemObject = itemsArray.getJSONObject(i);
                    Travel travel = parseTravelFromJson(itemObject);
                    if (travel != null) {
                        travels.add(travel);
                        totalCount++;
                        if (totalCount == totalCnt) {
                            break;
                        }
                    }
                }
            }
        } catch (IOException | JSONException e) {
            log.error("Error fetching travel list: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching travel list", e);
        }

        return travels;
    }

    private Travel parseTravelFromJson(JSONObject jsonTravel) {
        String contentid = jsonTravel.optString("contentid");
        String contenttypeid = jsonTravel.optString("contenttypeid");
        String title = jsonTravel.optString("title");
        String zipcode = jsonTravel.optString("zipcode");
        String addr1 = jsonTravel.optString("addr1");
        String addr2 = jsonTravel.optString("addr2", "");
        String tel = jsonTravel.optString("tel", "");
        String cat1 = jsonTravel.optString("cat1");
        String cat2 = jsonTravel.optString("cat2");
        String cat3 = jsonTravel.optString("cat3");
        String firstimage = jsonTravel.optString("firstimage", "");
        String firstimage2 = jsonTravel.optString("firstimage2", "");
        String booktour = jsonTravel.optString("booktour", "");
        String cpyrhtDivCd = jsonTravel.optString("cpyrhtDivCd", "");
        String mapx = jsonTravel.optString("mapx", "");
        String mapy = jsonTravel.optString("mapy", "");
        String mlevel = jsonTravel.optString("mlevel", "");
        String createdtime = jsonTravel.optString("createdtime", "");
        String modifiedtime = jsonTravel.optString("modifiedtime", "");
        String areacode = jsonTravel.optString("areacode", "");
        String sigungucode = jsonTravel.optString("sigungucode", "");
        int viewCnt = jsonTravel.optInt("viewCnt", 0);

        return Travel.builder()
                .contentid(contentid)
                .contenttypeid(contenttypeid)
                .title(title)
                .zipCode(zipcode)
                .addr1(addr1)
                .addr2(addr2)
                .tel(tel)
                .cat1(cat1)
                .cat2(cat2)
                .cat3(cat3)
                .firstimage(firstimage)
                .firstimage2(firstimage2)
                .booktour(booktour)
                .cpyrhtDivCd(cpyrhtDivCd)
                .mapx(mapx)
                .mapy(mapy)
                .mlevel(mlevel)
                .createdtime(createdtime)
                .modifiedtime(modifiedtime)
                .areaCode(areacode)
                .sigunguCode(sigungucode)
                .viewCnt(viewCnt)
                .build();
    }

    public JSONObject getDetailIntro(String contentId, String contentTypeId) {
        JSONObject itemObject;
        try {
            String url = buildUrl("detailIntro1", new String[]{
                    "contentId=" + URLEncoder.encode(contentId, "UTF-8"),
                    "contentTypeId=" + URLEncoder.encode(contentTypeId, "UTF-8")
            });
            String response = sendHttpRequest(url);

            JSONObject responseObject = new JSONObject(response);
            JSONObject responseBody = responseObject.getJSONObject("response").getJSONObject("body");
            JSONArray itemsArray = responseBody.optJSONObject("items").optJSONArray("item");
            itemObject = itemsArray.getJSONObject(0);
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

        return itemObject;
    }

    public Optional<TravelDetail> getDetailCommon(String contentId) {
        try {
            String url = buildUrl("detailCommon1", new String[]{
                    "contentId=" + URLEncoder.encode(contentId, "UTF-8"),
                    "defaultYN=Y",
                    "overviewYN=Y"
            });

            String response = sendHttpRequest(url);
            log.info(response);

            if (response.contains("<errMsg>")) {
                log.error(">>Unexpected API response for contentId: {}", contentId);
                return Optional.empty();
            }

            JSONObject responseBody = new JSONObject(response)
                    .getJSONObject("response")
                    .getJSONObject("body")
                    .optJSONObject("items");

            if (responseBody != null) {
                JSONObject itemObject = responseBody.optJSONArray("item").optJSONObject(0);

                if (itemObject != null) {
                    String homepage = itemObject.optString("homepage", "");
                    String overview = itemObject.optString("overview", "");

                    String contentTypeId = itemObject.optString("contenttypeid", "");
                    if (!contentTypeId.isEmpty()) {
                        TravelDetail detail = parseTravelDetailFromJson(homepage, overview, getDetailIntro(contentId, contentTypeId));
                        return Optional.ofNullable(detail);
                    } else {
                        return Optional.of(TravelDetail.builder()
                                .homepage(homepage)
                                .overview(overview)
                                .build());
                    }
                }
            }
            return Optional.empty();
        } catch (IOException | JSONException e) {
            log.error("Error retrieving travel detail for contentId: {}", contentId, e);
            return Optional.empty();
        }
    }

    private TravelDetail parseTravelDetailFromJson(String homepage, String overview, JSONObject jsonDetail) throws JSONException {
        String contentTypeId = jsonDetail.getString("contenttypeid");

        TravelDetailFactory factory;

        switch (contentTypeId) {
            case "12":
                factory = new TravelDetail12Factory();
                break;
            case "14":
                factory = new TravelDetail14Factory();
                break;
            case "15":
                factory = new TravelDetail15Factory();
                break;
            case "28":
                factory = new TravelDetail28Factory();
                break;
            default:
                return null;
        }

        return factory.createTravelDetail(homepage, overview, jsonDetail);
    }

    public String getContentForPage() {
        try {
            String url = buildUrl("areaBasedList1", new String[]{
                    "numOfRows=" + URLEncoder.encode(String.valueOf(1), "UTF-8"),
                    "pageNo=" + URLEncoder.encode(String.valueOf(1), "UTF-8")
            });
            return sendHttpRequest(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalCnt() {
        try {
            JSONObject jsonObject = new JSONObject(getContentForPage());
            JSONObject bodyObject = jsonObject.getJSONObject("response").getJSONObject("body");
            return bodyObject.getInt("totalCount");
        } catch (JSONException e) {
            throw new RuntimeException("Error parsing JSON response: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred: " + e.getMessage(), e);
        }
    }

    public List<Object> getAreaCodeList(String areaCode) throws UnsupportedEncodingException {
        List<Object> areaCodes = new ArrayList<>();
        StringBuilder url = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/areaCode1?")
                .append(URLEncoder.encode("serviceKey", "UTF-8")).append("=").append(secretKey)
                .append("&").append(URLEncoder.encode("_type", "UTF-8")).append("=").append(URLEncoder.encode("json", "UTF-8"))
                .append("&").append(URLEncoder.encode("MobileOS", "UTF-8")).append("=").append(URLEncoder.encode("ETC", "UTF-8"))
                .append("&").append(URLEncoder.encode("MobileApp", "UTF-8")).append("=").append(URLEncoder.encode("AppTest", "UTF-8"))
                .append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=").append(URLEncoder.encode("200", "UTF-8"))
                .append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=").append(URLEncoder.encode("AppTest", "UTF-8"));

        if (areaCode != null) {
            url.append("&").append(URLEncoder.encode("areaCode", "UTF-8")).append("=").append(URLEncoder.encode(areaCode, "UTF-8"));
        }

        try {
            String response = sendHttpRequest(url.toString());

            JSONObject responseBody = new JSONObject(response).getJSONObject("response").getJSONObject("body");
            System.out.println(responseBody.getInt("totalCount"));
            JSONArray itemsArray = responseBody.optJSONObject("items").optJSONArray("item");

            if (itemsArray != null) {
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject itemObject = itemsArray.getJSONObject(i);
                    if (areaCode != null) {
                        SigunguCode sigunguEntity = SigunguCode.builder()
                                .code(itemObject.optString("code", ""))
                                .name(itemObject.optString("name", ""))
                                .build();
                        areaCodes.add(sigunguEntity);
                    } else {
                        AreaCode areaEntity = AreaCode.builder()
                                .code(itemObject.optString("code", ""))
                                .name(itemObject.optString("name", ""))
                                .build();
                        areaCodes.add(areaEntity);
                    }
                }
            }
        } catch (IOException | JSONException e) {
            log.error("Error fetching travel list: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching travel list", e);
        }

        return areaCodes;
    }
}