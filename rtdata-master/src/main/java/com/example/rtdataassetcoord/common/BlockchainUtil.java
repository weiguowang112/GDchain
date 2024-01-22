package com.example.rtdataassetcoord.common;


import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BlockchainUtil {


    static RestTemplate restTemplate = new RestTemplate();
    //身份注册
    public  static void enroll(int spaceId,String username){

        //Register

        String url = "http://121.37.4.46:3002/identities";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", username);
        requestBody.put("type", "client");
        requestBody.put("maxEnrollments", 0);
        Map<String, String> responseMap = restTemplate.postForObject(url, requestBody, Map.class);


        //enroll
        url = "http://121.37.4.46:3002/identities/" + username + "/enroll";
        Map<String, String> enrollRequestBody = new HashMap<>();
        enrollRequestBody.put("secret", responseMap.get("secret"));
        restTemplate.postForObject(url, enrollRequestBody,String.class);
    }

    //通证转移
    public static void transfer(String userName, int tokenAmount, String tokenName) {
        String url = "http://121.37.4.46:3001/transactions";
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("type", "SendTransaction");
        headers.put("signer", "minter");
        headers.put("channel", "mychannel");
        headers.put("chaincode", "token_pool");
        requestBody.put("headers", headers);
        requestBody.put("func", "Transfer");
        ArrayList<String> args = new ArrayList<>();
        args.add(getAccoutIdByUserName(userName));
        args.add(String.valueOf(tokenAmount));
        args.add(tokenName);
        requestBody.put("args", args);
        requestBody.put("init", false);
        restTemplate.postForObject(url, requestBody, Map.class);
    }



    //通证燃烧
    public  static void burn(String userName,int tokenAmount, String tokenName){
        String url = "http://121.37.4.46:3002/transactions";
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("type", "SendTransaction");
        headers.put("signer", userName);
        headers.put("channel", "mychannel");
        headers.put("chaincode", "token_pool");
        requestBody.put("headers", headers);
        requestBody.put("func", "Burn");
        ArrayList<String> args = new ArrayList<>();
        args.add(String.valueOf(tokenAmount));
        args.add(tokenName);
        requestBody.put("args", args);
        requestBody.put("init", false);
        restTemplate.postForObject(url, requestBody, Map.class);

    }


    //getAccoutIdByUserName
    public  static String getAccoutIdByUserName(String userName){
        String url = "http://121.37.4.46:3002/query";
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("signer", userName);
        headers.put("channel", "mychannel");
        headers.put("chaincode", "token_pool");
        requestBody.put("headers", headers);
        requestBody.put("func", "ClientAccountID");
        requestBody.put("args", new ArrayList<>());
        requestBody.put("strongread", true);
        Map<String,String> map = restTemplate.postForObject(url, requestBody, Map.class);
        return map.get("result");
    }



    //PurchaseCopyright
    public  static void PurchaseCopyright(String hash,String title, String owner,String buyer){
        String url = "http://121.37.4.46:3001/transactions";
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("signer", "admin");
        headers.put("channel", "mychannel");
        headers.put("chaincode", "copyright");
        requestBody.put("headers", headers);
        requestBody.put("func", "PurchaseCopyright");
        ArrayList<String> args = new ArrayList<>();
        args.add(hash);
        args.add(title);
        args.add(owner);
        args.add(buyer);
        requestBody.put("args", args);
        restTemplate.postForObject(url, requestBody, Map.class);

    }










}
