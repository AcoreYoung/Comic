package com.leo.comic.ui;

import android.R.attr;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.bumptech.glide.Glide;
import com.leo.comic.R;
import com.leo.comic.bean.ComicMeanBean;
import com.leo.comic.tool.ExtractComicContent;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComicMean extends AppCompatActivity {
    private TextView comicTitle;
    private TextView comicIntro;
    private ImageView comicCover;
    private android.widget.Button continueReadingButton;

    private String mainHost = "https://www.manmanapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comic_mean);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        comicTitle = findViewById(R.id.comic_title);
        comicIntro = findViewById(R.id.comic_description);
        comicCover = findViewById(R.id.comic_cover);
        // 初始化底部按钮
        initBottomButton();

        Bundle bundle = getIntent().getExtras();
        String comicUrl = bundle.getString("comicOverallUrl");
        String comicName = bundle.getString("comicName");
        comicTitle.setText(comicName);
        Toast.makeText(this, "打开了漫画: " + comicName, Toast.LENGTH_SHORT).show();
        extractComicOverall(mainHost + comicUrl);
    }

    // 初始化底部按钮
    private void initBottomButton() {
        continueReadingButton = findViewById(R.id.continue_reading_button);
        // 适配深色模式
        if (isDarkMode()) {
            // 在深色模式下设置按钮背景和文字颜色
            continueReadingButton.setBackgroundColor(getResources().getColor(R.color.button_background_dark));
            continueReadingButton.setTextColor(getResources().getColor(R.color.button_text_dark));
        }
        continueReadingButton.setOnClickListener(v -> onContinueReadingClick());
    }
    private boolean isDarkMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode &
                android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
    // 继续阅读按钮点击事件处理
    private void onContinueReadingClick() {
        // 在这里处理继续阅读的逻辑
        // 例如：跳转到上次阅读的位置或第一个章节
        // 示例代码：
        // Intent intent = new Intent(this, ComicContent.class);
        // intent.putExtra("chapterIndex", 0); // 默认跳转到第一章
        // startActivity(intent);
    }

    private void onChapterItemClick(String chapterTitle, String chapterUrl) {
        // 在这里处理章节项点击事件

        Intent intent = new Intent(this, ComicContent.class);
        intent.putExtra("comicContentUrl", chapterUrl);
        intent.putExtra("comicChapterTitle", chapterTitle);
        startActivity(intent);
    }


    public void extractComicOverall(String comicUrl) {
        ComicMeanBean comicMean = new ComicMeanBean();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                String content = ExtractComicContent.getUrlContent(comicUrl);
                String coverImageUrl = extractCoverImageSrc(content);
                String describe = extractDescriptionContent(content);
                Map<String, String> comicList = extractComicListTitles(content);
                handler.post(() -> {
                    // 在主线程中处理返回的内容
//                    System.out.println(content);
                    comicMean.setComicLists(comicList);
                    comicMean.setComicIntro(describe);
                    comicMean.setComicCover(coverImageUrl);
                    drawComicMean(comicMean);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void drawComicMean(ComicMeanBean comicMean) {
//        comicTitle.setText(comicMean.getComicName());
        comicIntro.setText(comicMean.getComicIntro());
        String coverUrl = comicMean.getComicCover();
        if (coverUrl != null && !coverUrl.isEmpty()) {
            Glide.with(this)
                    .load(coverUrl)
//                    .placeholder(R.drawable.placeholder_image) // 可选：占位图
//                    .error(R.drawable.error_image) // 可选：错误图片
                    .into(comicCover);
        }
        addChapterItems(comicMean.getComicLists());
    }

    private void addChapterItems(Map<String, String> chapterTitles) {
        LinearLayout chapterListContainer = findViewById(R.id.chapter_list_container);

        // 清除现有的固定目录项（如果需要替换而不是追加）
        chapterListContainer.removeAllViews();

        // 将Map转换为List以保持顺序
        List<Map.Entry<String, String>> chapterList = new ArrayList<>(chapterTitles.entrySet());

        // 为每个章节标题创建一个TextView并添加到容器中
        for (int i = 0; i < chapterList.size(); i++) {
            final Map.Entry<String, String> entry = chapterList.get(i);
            TextView chapterItem = new TextView(this);
            chapterItem.setText(entry.getKey());
            chapterItem.setTextSize(14);
            chapterItem.setPadding(
                    dpToPx(12),
                    dpToPx(12),
                    dpToPx(12),
                    dpToPx(12)
            );

            // 添加边框样式
            chapterItem.setBackground(createBorderedBackground());
            chapterItem.setClickable(true);
            chapterItem.setFocusable(true);
            
            // 设置章节项的点击事件
            final int chapterIndex = i;
            chapterItem.setOnClickListener(v -> {
                // 处理章节点击事件
                onChapterItemClick(entry.getKey(), entry.getValue());
            });
            chapterListContainer.addView(chapterItem);
        }
    }
    private android.graphics.drawable.Drawable createBorderedBackground() {
        android.graphics.drawable.GradientDrawable drawable = new android.graphics.drawable.GradientDrawable();
        // 使用适配深色模式的颜色资源
        drawable.setColor(getResources().getColor(R.color.chapter_item_background_light));
        drawable.setStroke(dpToPx(1), getResources().getColor(R.color.chapter_item_border_light));
        drawable.setCornerRadius(dpToPx(4));
        return drawable;
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    private String extractCoverImageSrc(String content) {
        Pattern pattern = Pattern.compile("<li[^>]*class\\s*=\\s*[\"']pic[\"'][^>]*>.*?<img[^>]*src\\s*=\\s*[\"']([^\"']+)[\"'][^>]*/?>.*?</li>",
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return ""; // 如果没有找到匹配项，返回空字符串
    }

    private String extractDescriptionContent(String content) {
        // 使用正则表达式匹配class为"describe is_show_more"的元素的文字内容
        Pattern pattern = Pattern.compile("<[^>]+class\\s*=\\s*[\"'][^\"']*describe\\s+is_show_more[^\"']*[\"'][^>]*>(.*?)</[^>]+>",
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            String description = matcher.group(1);
            // 移除可能存在的HTML标签
            description = description.replaceAll("<[^>]+>", "").trim();
            return description;
        }
        return ""; // 如果没有找到匹配项，返回空字符串
    }

    private Map<String, String> extractComicListTitles(String content) {
        Map<String, String> titles = new LinkedHashMap<>();

        // 找到class为"comic_list"的ul标签
        Pattern ulPattern = Pattern.compile("<ul[^>]*class\\s*=\\s*[\"'][^\"']*comic_list[^\"']*[\"'][^>]*>(.*?)</ul>",
                Pattern.DOTALL);
        Matcher ulMatcher = ulPattern.matcher(content);

        if (ulMatcher.find()) {
            String ulContent = ulMatcher.group(1);

            // 修改正则表达式，直接匹配li标签中的a标签及其包含的h3标签
            Pattern liPattern = Pattern.compile("<li[^>]*>.*?<a[^>]*href\\s*=\\s*[\"']([^\"']+)[\"'][^>]*>.*?<h3[^>]*>(.*?)</h3>.*?</a>.*?</li>",
                    Pattern.DOTALL);
            Matcher liMatcher = liPattern.matcher(ulContent);

            // 提取所有匹配的li标签中的a标签href和h3标签文本内容
            while (liMatcher.find()) {
                String href = liMatcher.group(1); // 提取href属性值
                String title = liMatcher.group(2); // 提取h3标签文本内容

                // 移除可能存在的HTML标签
                title = title.replaceAll("<[^>]+>", "").trim();

                // 组合完整的URL
                String fullUrl = href.startsWith("http") ? href : mainHost + href;
                titles.put(title, fullUrl);
            }
        }

        return titles;
    }
}