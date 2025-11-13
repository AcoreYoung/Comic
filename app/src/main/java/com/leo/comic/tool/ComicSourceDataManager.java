package com.leo.comic.tool;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.leo.comic.bean.ComicSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ComicSourceDataManager {

    public static void parseAndSaveComicSource(Context context, String url) {
        // 将网络请求操作放到后台线程执行
        new Thread(() -> {
            try {
                // 从网络获取JSON内容
                String jsonContent = fetchJsonFromUrl(url);

                // 解析JSON数组
                JSONArray jsonArray = new JSONArray(jsonContent);
                ComicSourceDatabaseHelper dbHelper = new ComicSourceDatabaseHelper(context);

                int successCount = 0;
                int totalCount = jsonArray.length();

                // 遍历数组中的每个漫画源并保存
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ComicSource comicSource = parseComicSourceFromJson(jsonObject);

                        // 保存到数据库
                        long result = dbHelper.insertComicSource(comicSource);
                        if (result != -1) {
                            successCount++;
                        }
                    } catch (JSONException e) {
                        Log.e("ComicSourceDataManager", "Error parsing comic source at index " + i, e);
                    }
                }

                // 在主线程显示结果
                final int finalSuccessCount = successCount;
                final int finalTotalCount = totalCount;
                showToastOnMainThread(context,
                        String.format("导入完成: 成功 %d/%d 个漫画源", finalSuccessCount, finalTotalCount));

            } catch (JSONException e) {
                Log.e("ComicSourceDataManager", "JSON parsing error", e);
                showToastOnMainThread(context, "JSON格式错误: " + e.getMessage());
            } catch (IOException e) {
                Log.e("ComicSourceDataManager", "Network error", e);
                showToastOnMainThread(context, "网络请求失败: " + e.getMessage());
            } catch (Exception e) {
                Log.e("ComicSourceDataManager", "Unknown error", e);
                showToastOnMainThread(context, "未知错误: " + e.getMessage());
            }
        }).start();
    }

    private static String readFileContent(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        reader.close();
        fis.close();
        return content.toString();
    }

    /**
     * 从URL获取JSON数据
     * @param urlString URL地址
     * @return JSON字符串
     */
    private static String fetchJsonFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求参数
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000); // 10秒连接超时
        connection.setReadTimeout(10000);    // 10秒读取超时
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

        // 获取响应
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + responseCode);
        }

        // 读取响应内容
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        reader.close();
        inputStream.close();
        connection.disconnect();

        return content.toString();
    }

    /**
     * 在主线程显示Toast消息
     */
    private static void showToastOnMainThread(Context context, String message) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> {
            Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
        });
    }

    private static ComicSource parseComicSourceFromJson(JSONObject jsonObject) throws JSONException {
        ComicSource comicSource = new ComicSource();

        if (jsonObject.has("bookDelayTime")) {
            comicSource.setBookDelayTime(jsonObject.getString("bookDelayTime"));
        }
        if (jsonObject.has("bookSingleThread")) {
            comicSource.setBookSingleThread(jsonObject.getString("bookSingleThread"));
        }
        if (jsonObject.has("bookSourceGroup")) {
            comicSource.setBookSourceGroup(jsonObject.getString("bookSourceGroup"));
        }
        if (jsonObject.has("bookSourceName")) {
            comicSource.setBookSourceName(jsonObject.getString("bookSourceName"));
        }
        if (jsonObject.has("bookSourceType")) {
            comicSource.setBookSourceType(jsonObject.getString("bookSourceType"));
        }
        if (jsonObject.has("bookSourceUrl")) {
            comicSource.setBookSourceUrl(jsonObject.getString("bookSourceUrl"));
        }
        if (jsonObject.has("enable")) {
            comicSource.setEnable(jsonObject.getBoolean("enable"));
        }
        if (jsonObject.has("httpUserAgent")) {
            comicSource.setHttpUserAgent(jsonObject.getString("httpUserAgent"));
        }
        if (jsonObject.has("lastUpdateTime")) {
            comicSource.setLastUpdateTime(jsonObject.getLong("lastUpdateTime"));
        }
        if (jsonObject.has("loginUrl")) {
            comicSource.setLoginUrl(jsonObject.getString("loginUrl"));
        }
        if (jsonObject.has("loginUrlResult")) {
            comicSource.setLoginUrlResult(jsonObject.getString("loginUrlResult"));
        }
        if (jsonObject.has("ruleBookAuthor")) {
            comicSource.setRuleBookAuthor(jsonObject.getString("ruleBookAuthor"));
        }
        if (jsonObject.has("ruleBookContent")) {
            comicSource.setRuleBookContent(jsonObject.getString("ruleBookContent"));
        }
        if (jsonObject.has("ruleBookContentDecoder")) {
            comicSource.setRuleBookContentDecoder(jsonObject.getString("ruleBookContentDecoder"));
        }
        if (jsonObject.has("ruleBookKind")) {
            comicSource.setRuleBookKind(jsonObject.getString("ruleBookKind"));
        }
        if (jsonObject.has("ruleBookLastChapter")) {
            comicSource.setRuleBookLastChapter(jsonObject.getString("ruleBookLastChapter"));
        }
        if (jsonObject.has("ruleBookName")) {
            comicSource.setRuleBookName(jsonObject.getString("ruleBookName"));
        }
        if (jsonObject.has("ruleBookUrlPattern")) {
            comicSource.setRuleBookUrlPattern(jsonObject.getString("ruleBookUrlPattern"));
        }
        if (jsonObject.has("ruleChapterId")) {
            comicSource.setRuleChapterId(jsonObject.getString("ruleChapterId"));
        }
        if (jsonObject.has("ruleChapterList")) {
            comicSource.setRuleChapterList(jsonObject.getString("ruleChapterList"));
        }
        if (jsonObject.has("ruleChapterName")) {
            comicSource.setRuleChapterName(jsonObject.getString("ruleChapterName"));
        }
        if (jsonObject.has("ruleChapterParentId")) {
            comicSource.setRuleChapterParentId(jsonObject.getString("ruleChapterParentId"));
        }
        if (jsonObject.has("ruleChapterParentName")) {
            comicSource.setRuleChapterParentName(jsonObject.getString("ruleChapterParentName"));
        }
        if (jsonObject.has("ruleChapterUrl")) {
            comicSource.setRuleChapterUrl(jsonObject.getString("ruleChapterUrl"));
        }
        if (jsonObject.has("ruleChapterUrlNext")) {
            comicSource.setRuleChapterUrlNext(jsonObject.getString("ruleChapterUrlNext"));
        }
        if (jsonObject.has("ruleContentUrl")) {
            comicSource.setRuleContentUrl(jsonObject.getString("ruleContentUrl"));
        }
        if (jsonObject.has("ruleContentUrlNext")) {
            comicSource.setRuleContentUrlNext(jsonObject.getString("ruleContentUrlNext"));
        }
        if (jsonObject.has("ruleCoverDecoder")) {
            comicSource.setRuleCoverDecoder(jsonObject.getString("ruleCoverDecoder"));
        }
        if (jsonObject.has("ruleCoverUrl")) {
            comicSource.setRuleCoverUrl(jsonObject.getString("ruleCoverUrl"));
        }
        if (jsonObject.has("ruleFindUrl")) {
            comicSource.setRuleFindUrl(jsonObject.getString("ruleFindUrl"));
        }
        if (jsonObject.has("ruleIntroduce")) {
            comicSource.setRuleIntroduce(jsonObject.getString("ruleIntroduce"));
        }
        if (jsonObject.has("ruleSearchAuthor")) {
            comicSource.setRuleSearchAuthor(jsonObject.getString("ruleSearchAuthor"));
        }
        if (jsonObject.has("ruleSearchCoverDecoder")) {
            comicSource.setRuleSearchCoverDecoder(jsonObject.getString("ruleSearchCoverDecoder"));
        }
        if (jsonObject.has("ruleSearchCoverUrl")) {
            comicSource.setRuleSearchCoverUrl(jsonObject.getString("ruleSearchCoverUrl"));
        }
        if (jsonObject.has("ruleSearchKind")) {
            comicSource.setRuleSearchKind(jsonObject.getString("ruleSearchKind"));
        }
        if (jsonObject.has("ruleSearchLastChapter")) {
            comicSource.setRuleSearchLastChapter(jsonObject.getString("ruleSearchLastChapter"));
        }
        if (jsonObject.has("ruleSearchList")) {
            comicSource.setRuleSearchList(jsonObject.getString("ruleSearchList"));
        }
        if (jsonObject.has("ruleSearchName")) {
            comicSource.setRuleSearchName(jsonObject.getString("ruleSearchName"));
        }
        if (jsonObject.has("ruleSearchNoteUrl")) {
            comicSource.setRuleSearchNoteUrl(jsonObject.getString("ruleSearchNoteUrl"));
        }
        if (jsonObject.has("ruleSearchUrl")) {
            comicSource.setRuleSearchUrl(jsonObject.getString("ruleSearchUrl"));
        }
        if (jsonObject.has("ruleSearchUrlNext")) {
            comicSource.setRuleSearchUrlNext(jsonObject.getString("ruleSearchUrlNext"));
        }
        if (jsonObject.has("serialNumber")) {
            comicSource.setSerialNumber(jsonObject.getInt("serialNumber"));
        }
        if (jsonObject.has("sourceRemark")) {
            comicSource.setSourceRemark(jsonObject.getString("sourceRemark"));
        }
        if (jsonObject.has("weight")) {
            comicSource.setWeight(jsonObject.getInt("weight"));
        }

        return comicSource;
    }
}

