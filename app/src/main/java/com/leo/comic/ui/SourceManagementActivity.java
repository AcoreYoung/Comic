package com.leo.comic.ui;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.leo.comic.R;
import com.leo.comic.bean.ComicSource;
import com.leo.comic.tool.ComicSourceDataManager;
import com.leo.comic.tool.ComicSourceDatabaseHelper;

import java.util.List;

public class SourceManagementActivity extends AppCompatActivity {
    private boolean isMenuOpen = false;
    private LinearLayout menuMoreOptions;
    private LinearLayout importOverlay;
    private EditText editTextUrl;
    private Button btnCancel;
    private Button btnImport;
    private LinearLayout containerSources; // 添加容器引用
    private ComicSourceDatabaseHelper dbHelper; // 添加数据库帮助类引用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_management);

        initViews();
        setupClickListeners();
        loadData(); // 加载数据
    }

    private void setupClickListeners() {
        // 取消按钮点击事件
        btnCancel.setOnClickListener(v -> hideImportOverlay());

        // 导入按钮点击事件
        btnImport.setOnClickListener(v -> {
            String url = editTextUrl.getText().toString().trim();
            if (!url.isEmpty()) {
                // 调用网络导入功能
                ComicSourceDataManager.parseAndSaveComicSource(this.getBaseContext(), url);
                hideImportOverlay();

                loadData();
            } else {
                Toast.makeText(this, "请输入有效的URL", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // 显示遮罩层
    private void showImportOverlay() {
        editTextUrl.setText(""); // 清空输入框
        importOverlay.setVisibility(View.VISIBLE);
    }

    // 隐藏遮罩层
    private void hideImportOverlay() {
        importOverlay.setVisibility(View.GONE);
    }

    // 在触发网络导入的地方调用此方法
    private void onNetworkImportClicked() {
        showImportOverlay();
    }
    private void initViews() {
        ImageButton btnMore = findViewById(R.id.btn_more);
        menuMoreOptions = findViewById(R.id.menu_more_options);
        TextView menuAddSource = findViewById(R.id.menu_add_source);
        TextView menuImportLocal = findViewById(R.id.menu_import_local);
        TextView menuImportNetwork = findViewById(R.id.menu_import_network);
        importOverlay = findViewById(R.id.import_overlay);
        editTextUrl = findViewById(R.id.edit_text_url);
        btnCancel = findViewById(R.id.btn_cancel);
        btnImport = findViewById(R.id.btn_import);

        // 初始化图源容器
        containerSources = findViewById(R.id.container_sources);

        // 初始化数据库帮助类
        dbHelper = new ComicSourceDatabaseHelper(this);

        // 更多按钮点击事件
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenu();
            }
        });

        // 菜单项点击事件
        menuAddSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddSourceClick();
            }
        });

        menuImportLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImportLocalClick();
            }
        });

        menuImportNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImportNetworkClick();
            }
        });
    }

    private void toggleMenu() {
        if (isMenuOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    private void openMenu() {
        menuMoreOptions.setVisibility(View.VISIBLE);
        isMenuOpen = true;
    }

    private void closeMenu() {
        menuMoreOptions.setVisibility(View.GONE);
        isMenuOpen = false;
    }

    private void handleAddSourceClick() {
        // 处理增加图源点击事件
        Toast.makeText(this, "增加图源", Toast.LENGTH_SHORT).show();
        closeMenu();
    }

    private void handleImportLocalClick() {
        // 处理本地导入点击事件
        Toast.makeText(this, "本地导入", Toast.LENGTH_SHORT).show();
        closeMenu();
    }

    private void handleImportNetworkClick() {
        // 处理网络导入点击事件
        onNetworkImportClicked();
//        Toast.makeText(this, "网络导入", Toast.LENGTH_SHORT).show();
        closeMenu();
    }


    @Override
    public void onBackPressed() {
        if (isMenuOpen) {
            closeMenu();
        } else {
            super.onBackPressed();
        }
    }
    // 加载数据并显示在ScrollView中
    private void loadData() {
        new Thread(() -> {
            // 从数据库获取所有漫画源
            List<ComicSource> comicSources = dbHelper.getAllComicSources();

            // 在主线程更新UI
            runOnUiThread(() -> {
                // 清空现有视图
                containerSources.removeAllViews();

                // 为每个漫画源创建视图项
                for (ComicSource source : comicSources) {
                    View itemView = createSourceItemView(source);
                    containerSources.addView(itemView);
                }

                Toast.makeText(this, "获取到总共: " + comicSources.size() + " 条数据", Toast.LENGTH_SHORT).show();
            });

        }).start();
    }

    // 创建图源项视图
    private View createSourceItemView(ComicSource source) {
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_comic_source, containerSources, false);

        CheckBox cbSource = itemView.findViewById(R.id.cb_source);
        TextView tvSourceName = itemView.findViewById(R.id.tv_source_name);
        Button btnEdit = itemView.findViewById(R.id.btn_edit);
        Button btnDelete = itemView.findViewById(R.id.btn_delete);

        // 设置数据
        tvSourceName.setText(source.getBookSourceName());
        cbSource.setChecked(source.isEnable());

        // 设置按钮点击事件
        btnEdit.setOnClickListener(v -> {
            Toast.makeText(this, "编辑: " + source.getBookSourceName(), Toast.LENGTH_SHORT).show();
        });

        btnDelete.setOnClickListener(v -> {
            // 在后台线程中执行删除操作
            new Thread(() -> {
                int result = dbHelper.deleteComicSourceById(source.getId());

                // 在主线程更新UI
                runOnUiThread(() -> {
                    if (result > 0) {
                        Toast.makeText(this, "删除成功: " + source.getBookSourceName(), Toast.LENGTH_SHORT).show();
                        // 重新加载数据
                        loadData();
                    } else {
                        Toast.makeText(this, "删除失败: " + source.getBookSourceName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

        return itemView;
    }
}