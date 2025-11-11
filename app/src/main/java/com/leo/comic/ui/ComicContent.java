package com.leo.comic.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.leo.comic.R;
import com.leo.comic.tool.ExtractComicContent;
import okhttp3.OkHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComicContent extends AppCompatActivity {

    private ScrollView scrollView;
    private LinearLayout imagePanel;
    private List<String> imageContent = new ArrayList<>();
    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        // 先启用EdgeToEdge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comic_content);

        scrollView = findViewById(R.id.scroll_view);
        imagePanel = findViewById(R.id.image_panel);
        Bundle bundle = getIntent().getExtras();
        String comicUrl = bundle.getString("comicContentUrl");
        String comicChapterTitle = bundle.getString("comicChapterTitle");


        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> updateCurrentPageInfo(comicChapterTitle));

        extractComicContent(imagePanel, comicUrl);
    }


    private void addImageViews(LinearLayout container, List<String> imageContent) {
        this.imageContent = imageContent;
        container.removeAllViews();
        imageViews.clear();

        TextView comicInfoText = findViewById(R.id.comic_info_text);
        comicInfoText.setText("1/" + imageContent.size());

        for (String imageUrl : imageContent) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 0);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);

            // 使用Glide加载网络图片
            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.qq)
                    .error(R.drawable.qq)
                    .into(imageView);

            container.addView(imageView);
            imageViews.add(imageView);
        }
    }

    private void updateCurrentPageInfo(String comicChapterTitle) {
        if (imageViews.isEmpty() || imageContent.isEmpty()) return;

        int scrollY = scrollView.getScrollY();
        int currentImageIndex = 0;

        // 查找当前显示的图片索引
        for (int i = 0; i < imageViews.size(); i++) {
            ImageView imageView = imageViews.get(i);
            int[] location = new int[2];
            imageView.getLocationOnScreen(location);

            // 获取屏幕高度
            int screenHeight = getResources().getDisplayMetrics().heightPixels;

            // 如果图片在屏幕可视范围内，则认为是当前页
            if (location[1] < screenHeight && (location[1] + imageView.getHeight()) > 0) {
                currentImageIndex = i;
                break;
            }
        }

        // 更新页面信息显示（索引从0开始，所以需要+1）
        TextView comicInfoText = findViewById(R.id.comic_info_text);
        comicInfoText.setText(comicChapterTitle + " " + (currentImageIndex + 1) + "/" + imageContent.size());
    }

    public List<String> extractComicContent(LinearLayout imagePanel, String comicUrl) {
        List<String> comicContent = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                String content = ExtractComicContent.getUrlContent(comicUrl);
                List<String> imageUrls = extractImageSrc(content);
                handler.post(() -> {
                    // 在主线程中处理返回的内容
//                    System.out.println(content);
                    comicContent.addAll(imageUrls);
                    System.out.println("------------------------" + imageUrls.size());
                    addImageViews(imagePanel, imageUrls);
                });
            } catch (Exception e) {
            }
        });
        return comicContent;
    }



    private static List<String> extractImageSrc(String htmlContent) {
        List<String> imageSources = new ArrayList<>();
        // 使用正则表达式匹配class为man_img的img标签
        Pattern pattern = Pattern.compile("<img[^>]*class\\s*=\\s*[\"']man_img[\"'][^>]*src\\s*=\\s*[\"']([^\"']+)[\"'][^>]*/?>");
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            String src = matcher.group(1);
            imageSources.add(src);
        }
        return imageSources;
    }
}