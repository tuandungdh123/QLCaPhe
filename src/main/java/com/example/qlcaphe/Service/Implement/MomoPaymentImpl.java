package com.example.qlcaphe.Service.Implement;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MomoPaymentImpl {
    private static final String PARTNER_CODE = "MOMO";
    private static final String ACCESS_KEY = "F8BBA842ECF85";
    private static final String SECRET_KEY = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
    private static final String REDIRECT_URL = "https://momo.vn/return";
    private static final String IPN_URL = "https://callback.url/notify";
    private static final String REQUEST_TYPE = "captureWallet";

    public String createPaymentRequest(String orderInfo, String amount) throws Exception {
        String requestId = PARTNER_CODE + new Date().getTime();
        String orderId = requestId;
        String extraData = "";

        String rawSignature = "accessKey=" + ACCESS_KEY +
                "&amount=" + amount +
                "&extraData=" + extraData +
                "&ipnUrl=" + IPN_URL +
                "&orderId=" + orderId +
                "&orderInfo=" + orderInfo +
                "&partnerCode=" + PARTNER_CODE +
                "&redirectUrl=" + REDIRECT_URL +
                "&requestId=" + requestId +
                "&requestType=" + REQUEST_TYPE;

        String signature = hmacSHA256(rawSignature, SECRET_KEY);

        Map<String, String> requestBodyMap = new HashMap<>();
        requestBodyMap.put("partnerCode", PARTNER_CODE);
        requestBodyMap.put("accessKey", ACCESS_KEY);
        requestBodyMap.put("requestId", requestId);
        requestBodyMap.put("amount", amount);
        requestBodyMap.put("orderId", orderId);
        requestBodyMap.put("orderInfo", orderInfo);
        requestBodyMap.put("redirectUrl", REDIRECT_URL);
        requestBodyMap.put("ipnUrl", IPN_URL);
        requestBodyMap.put("extraData", extraData);
        requestBodyMap.put("requestType", "captureWallet");
        requestBodyMap.put("signature", signature);
        requestBodyMap.put("lang", "en");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(requestBodyMap);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://test-payment.momo.vn/v2/gateway/api/create");

        StringEntity entity = new StringEntity(requestBody);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

        client.close();

        Map<String, Object> jsonResponse = objectMapper.readValue(responseString, Map.class);
        return (String) jsonResponse.get("payUrl");
    }

    private String hmacSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return new String(Hex.encode(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8))));
    }
}
