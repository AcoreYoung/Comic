package com.leo.comic;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.leo.comic.databinding.ActivityMainBinding;
//import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.leo.comic.ui.SourceManagementActivity;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isMenuOpen = false;
    private FloatingActionButton fabMain;
    private LinearLayout menuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 实现沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+ 使用新API
            getWindow().setDecorFitsSystemWindows(false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Android 5.0-10 使用传统方法
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_history, R.id.navigation_local)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // 初始化悬浮按钮和菜单
        initFloatingActionButton();
//        addFloatingActionButton();
    }

    /*public void addFloatingActionButton() {
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(android.R.drawable.ic_input_add);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView sitemIcon = new ImageView(this);
        sitemIcon.setImageResource(android.R.drawable.ic_menu_search);
        SubActionButton searchButton = itemBuilder.setContentView(sitemIcon).build();
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(searchButton)
                .attachTo(actionButton)
                .build();
    }*/

    private void toggleMenu() {
        if (isMenuOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }



    private void handleSearchClick() {
        // 实现搜索功能
        // 例如跳转到搜索页面或打开搜索对话框

        Toast.makeText(this, "点击了搜索按钮", Toast.LENGTH_SHORT).show();
    }

    private void handleBrowseClick() {
        // 实现浏览功能
        // 例如跳转到浏览页面
        Toast.makeText(this, "点击了浏览按钮: ", Toast.LENGTH_SHORT).show();
    }

    // 新增图源管理点击处理方法
    private void handleSourceManagementClick() {
        // 实现图源管理功能
        Toast.makeText(this, "点击了图源管理", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SourceManagementActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (isMenuOpen) {
            closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    private View overlay;

    private void initFloatingActionButton() {
        fabMain = findViewById(R.id.fab_main);
        menuLayout = findViewById(R.id.menu_layout);
        overlay = findViewById(R.id.overlay);
//        TextView menuSearch = findViewById(R.id.menu_search);
//        TextView menuBrowse = findViewById(R.id.menu_browse);
        LinearLayout menuSourceManagement = findViewById(R.id.menu_source_management);
        LinearLayout menuSearch = findViewById(R.id.menu_search);
        LinearLayout menuBrowse = findViewById(R.id.menu_browse);

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenu();
            }
        });

        // 添加遮罩层点击监听器，点击关闭菜单
        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });


        // 图源管理点击事件
        menuSourceManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSourceManagementClick();
                closeMenu();
            }
        });

        menuSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSearchClick();
                closeMenu();
            }
        });

        menuBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBrowseClick();
                closeMenu();
            }
        });
    }

    private void openMenu() {
        overlay.setVisibility(View.VISIBLE);
        menuLayout.setVisibility(View.VISIBLE);
        menuLayout.animate().alpha(1.0f).setDuration(300).start();
        isMenuOpen = true;
        fabMain.animate().rotation(45f).setDuration(300).start();
    }

    private void closeMenu() {
        overlay.setVisibility(View.GONE);
        menuLayout.animate().alpha(0.0f).setDuration(300).start();
        menuLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isMenuOpen) return;
                menuLayout.setVisibility(View.GONE);
            }
        }, 300);
        isMenuOpen = false;
        fabMain.animate().rotation(0f).setDuration(300).start();
    }
}