package com.leo.comic.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.leo.comic.R;
import com.leo.comic.bean.ComicMeanBean;
import com.leo.comic.tool.ExtractComicContent;
import com.leo.comic.ui.ComicContent;
import com.leo.comic.databinding.FragmentHomeBinding;
import com.leo.comic.ui.ComicMean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;

    private String findUrl = "全部::https://www.manmanapp.com/comic/category.html\n" +
            "女频::https://www.manmanapp.com/comic/category-21.html\n" +
            "大陆漫画::/sort/?category=3&tag=&page=searchPage\n" +
            "韩国漫画::/sort/?category=4&tag=&page=searchPage\n" +
            "悬疑::/sort/?category=&tag=1&page=searchPage\n" +
            "欢乐::/sort/?category=&tag=2&page=searchPage\n" +
            "搞笑::/sort/?category=&tag=3&page=searchPage\n" +
            "玄幻::/sort/?category=&tag=4&page=searchPage\n" +
            "冒险::/sort/?category=&tag=5&page=searchPage\n" +
            "爱情::/sort/?category=&tag=6&page=searchPage\n" +
            "百合::/sort/?category=&tag=7&page=searchPage\n" +
            "推理::/sort/?category=&tag=8&page=searchPage\n" +
            "热血::/sort/?category=&tag=9&page=searchPage\n" +
            "异界::/sort/?category=&tag=10&page=searchPage\n" +
            "轻改::/sort/?category=&tag=11&page=searchPage\n" +
            "奇幻::/sort/?category=&tag=12&page=searchPage\n" +
            "校园::/sort/?category=&tag=13&page=searchPage\n" +
            "妹控::/sort/?category=&tag=14&page=searchPage\n" +
            "生活::/sort/?category=&tag=15&page=searchPage\n" +
            "竞技::/sort/?category=&tag=16&page=searchPage\n" +
            "同人::/sort/?category=&tag=17&page=searchPage\n" +
            "伪娘::/sort/?category=&tag=18&page=searchPage\n" +
            "东方::/sort/?category=&tag=19&page=searchPage\n" +
            "美食::/sort/?category=&tag=20&page=searchPage\n" +
            "格斗::/sort/?category=&tag=21&page=searchPage\n" +
            "战争::/sort/?category=&tag=22&page=searchPage\n" +
            "舰娘::/sort/?category=&tag=23&page=searchPage\n" +
            "治愈::/sort/?category=&tag=24&page=searchPage\n" +
            "魔幻::/sort/?category=&tag=25&page=searchPage\n" +
            "职场::/sort/?category=&tag=26&page=searchPage\n" +
            "性转::/sort/?category=&tag=27&page=searchPage\n" +
            "萌系::/sort/?category=&tag=28&page=searchPage\n" +
            "后宫::/sort/?category=&tag=29&page=searchPage\n" +
            "节操::/sort/?category=&tag=30&page=searchPage\n" +
            "魔法::/sort/?category=&tag=31&page=searchPage\n" +
            "科幻::/sort/?category=&tag=32&page=searchPage\n" +
            "穿越::/sort/?category=&tag=33&page=searchPage\n" +
            "仙侠::/sort/?category=&tag=34&page=searchPage\n" +
            "都市::/sort/?category=&tag=35&page=searchPage\n" +
            "异能::/sort/?category=&tag=36&page=searchPage\n" +
            "网游::/sort/?category=&tag=37&page=searchPage\n" +
            "短篇::/sort/?category=&tag=38&page=searchPage\n" +
            "女主::/sort/?category=&tag=39&page=searchPage\n" +
            "恐怖::/sort/?category=&tag=40&page=searchPage\n" +
            "重生::/sort/?category=&tag=41&page=searchPage\n" +
            "日常::/sort/?category=&tag=42&page=searchPage\n" +
            "侦探::/sort/?category=&tag=43&page=searchPage\n" +
            "言情::/sort/?category=&tag=44&page=searchPage\n" +
            "生存::/sort/?category=&tag=45&page=searchPage\n" +
            "武侠::/sort/?category=&tag=46&page=searchPage\n" +
            "系统::/sort/?category=&tag=47&page=searchPage\n" +
            "励志::/sort/?category=&tag=48&page=searchPage\n" +
            "末世::/sort/?category=&tag=49&page=searchPage\n" +
            "转生::/sort/?category=&tag=50&page=searchPage\n" +
            "长篇::/sort/?category=&tag=51&page=searchPage\n" +
            "修仙::/sort/?category=&tag=52&page=searchPage\n" +
            "末日::/sort/?category=&tag=53&page=searchPage\n" +
            "灵异::/sort/?category=&tag=54&page=searchPage\n" +
            "游戏::/sort/?category=&tag=55&page=searchPage\n" +
            "爱倩::/sort/?category=&tag=56&page=searchPage\n" +
            "销毁::/sort/?category=&tag=57&page=searchPage\n" +
            "病娇::/sort/?category=&tag=58&page=searchPage\n" +
            "音乐::/sort/?category=&tag=59&page=searchPage\n" +
            "爆笑::/sort/?category=&tag=60&page=searchPage\n" +
            "机战::/sort/?category=&tag=61&page=searchPage\n" +
            "复仇::/sort/?category=&tag=62&page=searchPage\n" +
            "舞蹈::/sort/?category=&tag=63&page=searchPage";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        addCategoryTabs();
        String firstTab = convertFindUrlToArray(findUrl)[0];
        onTabClick(firstTab.split("::")[0], firstTab.split("::")[1]);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String[] convertFindUrlToArray(String url) {
        return url.split("\n");
    }
    private void addCategoryTabs() {
        LinearLayout categoryContainer = binding.categoryContainer;
        categoryContainer.removeAllViews();

        for (String item : convertFindUrlToArray(findUrl)) {
            String[] parts = item.split("::");
            if (parts.length >= 1) {
                String tabName = parts[0];
                TextView tab = new TextView(getContext());
                tab.setText(tabName);
                tab.setPadding(dpToPx(12), dpToPx(8), dpToPx(12), dpToPx(8));
//                tab.setMarginEnd(dpToPx(8));
                tab.setBackgroundResource(R.drawable.tab_background);
                tab.setTextColor(getResources().getColorStateList(R.color.tab_text_color));
                tab.setClickable(true);
                tab.setFocusable(true);

                // 设置标签点击事件
                tab.setOnClickListener(v -> onTabClick(tabName, parts.length > 1 ? parts[1] : ""));

                categoryContainer.addView(tab);
            }
        }
    }

    private void onTabClick(String tabName, String url) {
        // 处理标签点击事件
        Toast.makeText(getContext(), "点击了标签: " + tabName + ", URL: " + url, Toast.LENGTH_SHORT).show();

        // 在实际项目中，这里应该发起网络请求获取页面内容并解析
        // 示例代码如下：

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                String content = ExtractComicContent.getUrlContent(url);
                List<ComicMeanBean> comics = parseComicItems(content);

                handler.post(() -> {
                    // 更新UI，显示解析到的漫画列表
                    displayComics(comics);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    // 解析漫画项目的方法
    private List<ComicMeanBean> parseComicItems(String content) {
        List<ComicMeanBean> comics = new ArrayList<>();

        // 查找所有class是classification的div标签
        Pattern divPattern = Pattern.compile("<div[^>]*class\\s*=\\s*[\"'][^\"']*classification[^\"']*[\"'][^>]*>(.*?)</div>",
                Pattern.DOTALL);
        Matcher divMatcher = divPattern.matcher(content);

        while (divMatcher.find()) {
            String divContent = divMatcher.group(1);
            ComicMeanBean comic = new ComicMeanBean();

            // 查找title ellipsis的li标签文本内容作为漫画标题
            Pattern titlePattern = Pattern.compile("<li[^>]*class\\s*=\\s*[\"'][^\"']*title\\s+ellipsis[^\"']*[\"'][^>]*>(.*?)</li>",
                    Pattern.DOTALL);
            Matcher titleMatcher = titlePattern.matcher(divContent);
            if (titleMatcher.find()) {
                String title = titleMatcher.group(1);
                title = title.replaceAll("<[^>]+>", "").trim();
                comic.setComicName(title);
            }

            // 查找subtitle ellipsis的li标签文本内容作为漫画作者
            Pattern authorPattern = Pattern.compile("<li[^>]*class\\s*=\\s*[\"'][^\"']*subtitle\\s+ellipsis[^\"']*[\"'][^>]*>(.*?)</li>",
                    Pattern.DOTALL);
            Matcher authorMatcher = authorPattern.matcher(divContent);
            if (authorMatcher.find()) {
                String author = authorMatcher.group(1);
                author = author.replaceAll("<[^>]+>", "").trim();
                // 注意：ComicMeanBean中没有专门的作者字段，可以考虑使用其他字段或扩展类
                comic.setComicIntro("作者: " + author);
            }

            // 查找a标签的src作为漫画封面，href作为漫画地址
            Pattern aPattern = Pattern.compile("<a[^>]*href\\s*=\\s*[\"']([^\"']+)[\"'][^>]*>.*?<img[^>]*src\\s*=\\s*[\"']([^\"']+)[\"'][^>]*/?>",
                    Pattern.DOTALL);
            Matcher aMatcher = aPattern.matcher(divContent);
            if (aMatcher.find()) {
                String href = aMatcher.group(1);
                String src = aMatcher.group(2);
                comic.setComicCover(src);
                // 注意：ComicMeanBean中没有专门的漫画地址字段，可以考虑使用其他字段或扩展类
                comic.setComicUrl(href);
            }

            comics.add(comic);
        }

        return comics;
    }

    private void displayComics(List<ComicMeanBean> comics) {
        LinearLayout imagePanel = binding.imagePanel;
        imagePanel.removeAllViews();

        for (ComicMeanBean comic : comics) {
            // 创建漫画项的布局
            LinearLayout comicItemLayout = new LinearLayout(getContext());
            comicItemLayout.setOrientation(LinearLayout.HORIZONTAL);
            comicItemLayout.setPadding(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8));

            // 创建漫画封面 ImageView
            ImageView coverImage = new ImageView(getContext());
            coverImage.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(80), dpToPx(100)));
            coverImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            coverImage.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

            // 使用 Glide 加载封面图片（如果有的话）
            if (comic.getComicCover() != null && !comic.getComicCover().isEmpty()) {
                // 注意：这里需要确保 Glide 已正确导入
                 Glide.with(this).load(comic.getComicCover()).into(coverImage);

            }

            // 创建右侧信息区域
            LinearLayout infoLayout = new LinearLayout(getContext());
            infoLayout.setOrientation(LinearLayout.VERTICAL);
            infoLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            infoLayout.setPadding(dpToPx(12), 0, 0, 0);

            // 创建标题 TextView
            TextView titleText = new TextView(getContext());
            titleText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            titleText.setText(comic.getComicName() != null ? comic.getComicName() : "未知标题");
            titleText.setTextSize(16);
            titleText.setTypeface(null, android.graphics.Typeface.BOLD);
            titleText.setEllipsize(TextUtils.TruncateAt.END);
            titleText.setMaxLines(1);

            // 创建作者 TextView
            TextView authorText = new TextView(getContext());
            authorText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            authorText.setText(comic.getComicIntro() != null ? comic.getComicIntro() : "未知作者");
            authorText.setTextSize(14);
            authorText.setPadding(0, dpToPx(4), 0, 0);

            // 将组件添加到布局中
            infoLayout.addView(titleText);
            infoLayout.addView(authorText);

            comicItemLayout.addView(coverImage);
            comicItemLayout.addView(infoLayout);

            // 为漫画项添加点击事件
            comicItemLayout.setOnClickListener(v -> {

                Toast.makeText(getContext(), "点击了漫画: " + comic.getComicName() + ", URL: " + comic.getComicUrl(), Toast.LENGTH_SHORT).show();
                // 处理漫画项点击事件，跳转到漫画详情页
                Intent intent = new Intent(getActivity(), ComicMean.class);
                // 这里需要传递漫画的URL，但目前 ComicMeanBean 中没有存储URL的字段
                // 可以考虑扩展 ComicMeanBean 或使用其他方式传递
                intent.putExtra("comicOverallUrl", comic.getComicUrl());
                startActivity(intent);
            });

            imagePanel.addView(comicItemLayout);
        }
    }
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }


    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(getActivity(), ComicContent.class);
//        intent.putExtra("comicContentUrl", "https://www.manmanapp.com/comic/detail-1565234.html");
        Intent intent = new Intent(getActivity(), ComicMean.class);
        intent.putExtra("comicOverallUrl", "https://www.manmanapp.com/comic-1405394.html");
        startActivity(intent);
    }
}