package com.green.Nolloo.restAPI.service;

import com.green.Nolloo.restAPI.vo.AddressVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.client.HttpClientErrorException;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Service
public class KakaoApiService {

    @Value("${kakao.api.key}")
    private String apiKey;

    public AddressVO getAddressFromGeolocation(double latitude, double longitude) {
        String url = String.format("https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?input_coord=WGS84&output_coord=WGS84&x=%f&y=%f", longitude, latitude);
        AddressVO addressVO = new AddressVO();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<Map<String, Object>>() {});
            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();
                List<Map<String, Object>> documents = (List<Map<String, Object>>) responseBody.get("documents");
                if (!documents.isEmpty()) {
                    Map<String, Object> firstDocument = documents.get(0);

                    System.out.println(firstDocument);

                    addressVO.setRegion1depthName((String) firstDocument.get("region_1depth_name"));
                    addressVO.setRegion2depthName((String) firstDocument.get("region_2depth_name"));
                    addressVO.setRegion3depthName((String) firstDocument.get("region_3depth_name"));

//                  String addressName = (String) firstDocument.get("address_name");
//                  System.out.println(addressName);

                    return addressVO;

                } else {
                    System.out.println("Error: No documents found in the response");
                }
            } else {
                System.out.println("Error: Unexpected response status - " + response.getStatusCodeValue());
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Error: HTTP client exception - " + e.getRawStatusCode() + ": " + e.getStatusText());
        } catch (Exception e) {
            System.out.println("Error: Exception - " + e.getMessage());
        }
        return null;
    }

}
