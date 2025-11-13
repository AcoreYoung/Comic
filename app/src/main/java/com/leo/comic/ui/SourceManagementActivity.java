package com.leo.comic.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.leo.comic.R;

public class SourceManagementActivity extends AppCompatActivity {
    private boolean isMenuOpen = false;
    private LinearLayout menuMoreOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_management);

        initViews();
    }

    private void initViews() {
        ImageButton btnMore = findViewById(R.id.btn_more);
        menuMoreOptions = findViewById(R.id.menu_more_options);
        TextView menuAddSource = findViewById(R.id.menu_add_source);
        TextView menuImportLocal = findViewById(R.id.menu_import_local);
        TextView menuImportNetwork = findViewById(R.id.menu_import_network);

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
        Toast.makeText(this, "网络导入", Toast.LENGTH_SHORT).show();
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
}