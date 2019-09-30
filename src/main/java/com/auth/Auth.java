package com.auth;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Auth {
    /* Auth Properties */
    private String client_id = "RWFQ451LX9UXO3N1ADUPTZZWPLKN6RBR";
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private int aExpiresIn;
    private int rExpiresIn;

    /* File Properties */
    private String path = "/Users/Jason/Projects/Java/StockScanner/src/main/resources/json/auth.json";

    public Auth() {
        refreshTokens();
    }

    private void refreshTokens() {
        readFromFile(); // Read stored tokens
        HttpResponse<JsonNode> response = Unirest.post("https://api.tdameritrade.com/v1/oauth2/token")
                .header("Content-Type","application/x-www-form-urlencoded")
                .field("grant_type","refresh_token")
                .field("refresh_token", refreshToken)
                .field("access_type", "offline")
                .field("client_id", client_id)
                .field("redirect_uri","http://localhost")
                .asJson();
        // Receive new tokens and save them to file
        accessToken = response.getBody().getObject().get("access_token").toString();
        refreshToken = response.getBody().getObject().get("refresh_token").toString();
        aExpiresIn = Integer.parseInt(response.getBody().getObject().get("expires_in").toString());
        rExpiresIn = Integer.parseInt(response.getBody().getObject().get("refresh_token_expires_in").toString());
        tokenType = response.getBody().getObject().get("token_type").toString();
        writeToFile(); // Write refreshed tokens
    }

    private void readFromFile() {
        try {
            JSONParser parser = new JSONParser();
            Object objAuth = parser.parse(new FileReader(path));
            JSONObject jsonAuth = (JSONObject) objAuth;

            String accessToken = (String) jsonAuth.get("access_token");
            String refreshToken = (String) jsonAuth.get("refresh_token");
            String tokenType = (String) jsonAuth.get("token_type");
            int aExpiresIn = Integer.parseInt(jsonAuth.get("expires_in").toString());
            int rExpiresIn = Integer.parseInt(jsonAuth.get("refresh_token_expires_in").toString());

            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.tokenType = tokenType;
            this.aExpiresIn = aExpiresIn;
            this.rExpiresIn = rExpiresIn;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        JSONObject auth = new JSONObject();
        auth.put("access_token", accessToken);
        auth.put("refresh_token", refreshToken);
        auth.put("expires_in", aExpiresIn);
        auth.put("refresh_token_expires_in", rExpiresIn);
        auth.put("token_type", tokenType);

        try (FileWriter file = new FileWriter(path)) {
            file.write(auth.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() { return tokenType; }

    public int getaExpiresIn() {
        return aExpiresIn;
    }

    public int getrExpiresIn() {
        return rExpiresIn;
    }

    public String getClientId() {
        return client_id;
    }
}
