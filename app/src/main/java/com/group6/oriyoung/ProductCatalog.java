package com.group6.oriyoung;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group6.adapters.CatalogAdapter;
import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityProductCatalogBinding;

import java.util.ArrayList;

public class ProductCatalog extends BaseActivity {
    ActivityProductCatalogBinding binding;
    ArrayList<Product> catalog;
    CatalogAdapter catalogAdapter;

    int categoryID;
    String categoryName;
    String searchText;
    boolean isSearch;

    // Khai báo biến để theo dõi trạng thái của bottom sheet và trạng thái của nút
    private boolean isCatalogBottomSheetVisible = false;
    private boolean isFilterBottomSheetVisible = false;
    private boolean isSortBottomSheetVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductCatalogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();

        filterEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void getIntentExtra() {
        categoryID = getIntent().getIntExtra("categoryID", 0);
        categoryName = getIntent().getStringExtra("categoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

        binding.toolbar.toolbarTitle.setText(categoryName);
        binding.toolbar.btnBack.setOnClickListener(v -> finish());
        loadDataCatalog();
    }

    private void loadDataCatalog() {
        DatabaseReference myRef = database.getReference("Product");
        binding.progressBarListProduct.setVisibility(View.VISIBLE);
        catalog = new ArrayList<>();

        Query query;
        if (isSearch) {
            query = myRef.orderByChild("productName").startAt(searchText).endAt(searchText + '\uf8ff');
        } else {
            if (categoryID == 7) {
                query = myRef;
            } else {
                query = myRef.orderByChild("categoryID").equalTo(categoryID);
            }
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        catalog.add(issue.getValue(Product.class));
                    }
                    if (catalog.size() > 0) {
                        GridLayoutManager layoutManager = new GridLayoutManager(ProductCatalog.this, 2, RecyclerView.VERTICAL, false);
                        binding.rvCatalog.setLayoutManager(layoutManager);
                        binding.rvCatalog.setHasFixedSize(true);
                        catalogAdapter = new CatalogAdapter(getApplicationContext(), catalog);
                        binding.rvCatalog.setAdapter(catalogAdapter);
                    }
                    binding.progressBarListProduct.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Xử lý dialog
    private void resetButtonBackground() {
        binding.imvSort.setBackgroundResource(R.drawable.linear_bg_state);
        binding.imvFilter.setBackgroundResource(R.drawable.linear_bg_state);
        binding.imvCatalog.setBackgroundResource(R.drawable.linear_bg_state);
    }

    private Dialog getBottomSheetDialog(View view) {
        Dialog dialog = new Dialog(ProductCatalog.this);
        // Xử lý hiển thị bottom sheet tương ứng với view
        if (view == binding.imvSort) {
            dialog.setContentView(R.layout.bottomsheet_sort);
        } else if (view == binding.imvFilter) {
            dialog.setContentView(R.layout.bottomsheet_filter);
        } else if (view == binding.imvCatalog) {
            dialog.setContentView(R.layout.bottomsheet_list);
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        return dialog;
    }

    private void filterEvent() {
        // Set onClickListener cho imvSort
        binding.imvSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đổi màu nền của imvSort
                binding.imvSort.setBackgroundResource(R.drawable.linear_bg_selected_state);

                // Hiển thị bottom sheet tương ứng
                Dialog dialog = getBottomSheetDialog(binding.imvSort);
                dialog.show();

                // Xử lý sự kiện khi bottom sheet bị ẩn đi
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // Reset màu nền của imvSort
                        binding.imvSort.setBackgroundResource(R.drawable.linear_bg_state);
                    }
                });
            }
        });

        // Set onClickListener cho imvFilter
        binding.imvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đổi màu nền của imvFilter
                binding.imvFilter.setBackgroundResource(R.drawable.linear_bg_selected_state);

                // Hiển thị bottom sheet tương ứng
                Dialog dialog = getBottomSheetDialog(binding.imvFilter);
                dialog.show();

                // Xử lý sự kiện khi bottom sheet bị ẩn đi
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // Reset màu nền của imvFilter
                        binding.imvFilter.setBackgroundResource(R.drawable.linear_bg_state);
                    }
                });
            }
        });

        // Set onClickListener cho imvCatalog
        binding.imvCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đổi màu nền của imvCatalog
                binding.imvCatalog.setBackgroundResource(R.drawable.linear_bg_selected_state);

                // Hiển thị bottom sheet tương ứng
                Dialog dialog = getBottomSheetDialog(binding.imvCatalog);
                dialog.show();

                // Xử lý sự kiện khi bottom sheet bị ẩn đi
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // Reset màu nền của imvCatalog
                        binding.imvCatalog.setBackgroundResource(R.drawable.linear_bg_state);
                    }
                });
            }
        });
    }

    private void handleBottomSheet(View view, boolean isVisible) {
        if (!isVisible) {
            Dialog dialog = getBottomSheetDialog(view);
            dialog.show();
            view.setBackgroundResource(R.drawable.linear_bg_selected_state);
        } else {
            Dialog dialog = getBottomSheetDialog(view);
            dialog.dismiss();
            resetButtonBackground();
        }
    }
}
