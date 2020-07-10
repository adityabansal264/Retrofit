package com.example.reterofit;

import org.json.JSONObject;

public class Articles {
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;
    public String content;

    public Source source;

    public static Articles parseJSONObject(JSONObject jsonObject){
        Articles item=new Articles();
        item.author = jsonObject.optString("author");
        item.title=jsonObject.optString("title");
        item.description=jsonObject.optString("description");
        item.urlToImage = jsonObject.optString("urlToImage");
        item.publishedAt = jsonObject.optString("publishedAt");
        item.content = jsonObject.optString("content");
        item.url = jsonObject.optString("url");

        JSONObject sourceObject = jsonObject.optJSONObject("source");
        item.source = Source.parseSourceJSON(sourceObject);

        return item;
    }
}
