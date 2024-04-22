package com.group6.oriyoung;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
import java.util.Collections;
import java.util.Comparator;

public class ProductCatalog extends BaseActivity {
    ActivityProductCatalogBinding binding;
    ArrayList<Product> catalog;
    CatalogAdapter catalogAdapter;

    int categoryID;
    String categoryName;
    String searchText;
    boolean isSearch;

    private DatabaseReference databaseRef;
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
        databaseRef = database.getReference("Product");
        binding.progressBarListProduct.setVisibility(View.VISIBLE);
        catalog = new ArrayList<>();

        Query query;
        if (isSearch) {
            query = databaseRef.orderByChild("productName").startAt(searchText).endAt(searchText + '\uf8ff');
        } else {
            if (categoryID == 7) {
                query = databaseRef;
            } else {
                query = databaseRef.orderByChild("categoryID").equalTo(categoryID);
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

    private Dialog getBottomSheetDialog(View view) {
        Dialog dialog = new Dialog(ProductCatalog.this);
        // Handle displaying the corresponding bottom sheet based on the view
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
        // Set onClickListener for imvSort
        binding.imvSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the background color of imvSort
                binding.imvSort.setBackgroundResource(R.drawable.linear_bg_selected_state);

                // Display the corresponding bottom sheet
                Dialog dialog = getBottomSheetDialog(binding.imvSort);
                dialog.show();

                // Handle the event when the bottom sheet is dismissed
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // Reset the background color of imvSort
                        binding.imvSort.setBackgroundResource(R.drawable.linear_bg_state);
                    }
                });

                RadioButton radioButtonName = dialog.findViewById(R.id.radioButtonName);
                RadioButton radioButtonPriceAscending = dialog.findViewById(R.id.radioButtonPriceAscending);
                RadioButton radioButtonPriceDescending = dialog.findViewById(R.id.radioButtonPriceDescending);

                radioButtonName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Sort the catalog by product name (A-Z)
                        Collections.sort(catalog, new Comparator<Product>() {
                            @Override
                            public int compare(Product p1, Product p2) {
                                return p1.getProductName().compareToIgnoreCase(p2.getProductName());
                            }
                        });
                        // Update the RecyclerView adapter with sorted data
                        catalogAdapter.notifyDataSetChanged();
                    }
                });

                radioButtonPriceAscending.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Sort the catalog by price in ascending order
                        Collections.sort(catalog, new Comparator<Product>() {
                            @Override
                            public int compare(Product p1, Product p2) {
                                return Double.compare(p1.getProductPrice(), p2.getProductPrice());
                            }
                        });
                        // Update the RecyclerView adapter with sorted data
                        catalogAdapter.notifyDataSetChanged();
                    }
                });

                radioButtonPriceDescending.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Sort the catalog by price in descending order
                        Collections.sort(catalog, new Comparator<Product>() {
                            @Override
                            public int compare(Product p1, Product p2) {
                                return Double.compare(p2.getProductPrice(), p1.getProductPrice());
                            }
                        });
                        // Update the RecyclerView adapter with sorted data
                        catalogAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        // Set onClickListener for imvFilter
        binding.imvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the background color of imvFilter
                binding.imvFilter.setBackgroundResource(R.drawable.linear_bg_selected_state);

                // Display the corresponding bottom sheet
                Dialog dialog = getBottomSheetDialog(binding.imvFilter);
                dialog.show();

                // Handle the event when the bottom sheet is dismissed
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // Reset the background color of imvFilter
                        binding.imvFilter.setBackgroundResource(R.drawable.linear_bg_state);
                    }
                });
            }
        });

        // Set onClickListener for imvCatalog
        binding.imvCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the background color of imvCatalog
                binding.imvCatalog.setBackgroundResource(R.drawable.linear_bg_selected_state);

                // Display the corresponding bottom sheet
                Dialog dialog = getBottomSheetDialog(binding.imvCatalog);
                dialog.show();

                // Handle the event when the bottom sheet is dismissed
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // Reset the background color of imvCatalog
                        binding.imvCatalog.setBackgroundResource(R.drawable.linear_bg_state);
                    }
                });
            }
        });
    }
}
