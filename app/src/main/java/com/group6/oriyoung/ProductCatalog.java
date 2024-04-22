package com.group6.oriyoung;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    ArrayList<Product> originalCatalog;
    ArrayList<Product> filteredCatalog;

    int categoryID;
    String categoryName;
    String searchText;
    boolean isSearch;

    private DatabaseReference databaseRef;

    // Flags to track checkbox states
    private boolean isUnder100kChecked = false;
    private boolean is100kTo250kChecked = false;
    private boolean is250kTo400kChecked = false;
    private boolean isOver400kChecked = false;
    private boolean isAnyCheckboxChecked =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductCatalogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        filterEvent();
        originalCatalog = new ArrayList<>();
        filteredCatalog = new ArrayList<>();
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
                        originalCatalog.addAll(catalog);
                        filteredCatalog.addAll(catalog);
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
                        // Check if any checkbox is checked
                        if (!isAnyCheckboxChecked) {
                            // If no checkbox is checked, reset the catalog to original state
                            resetCatalog();
                        }
                        // Reset the flag
                        isAnyCheckboxChecked = false;
                    }
                });

                // Get checkboxes from the bottom sheet layout
                CheckBox checkBoxUnder100k = dialog.findViewById(R.id.checkBoxUnder100k);
                CheckBox checkBox100kTo250k = dialog.findViewById(R.id.checkBox100kTo250k);
                CheckBox checkBox250kTo400k = dialog.findViewById(R.id.checkBox250kTo400k);
                CheckBox checkBoxOver400k = dialog.findViewById(R.id.checkBoxOver400k);

                // Set onClickListener for each checkbox
                checkBoxUnder100k.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Cập nhật trạng thái của biến khi checkbox được chọn hoặc bỏ chọn
                        isUnder100kChecked = checkBoxUnder100k.isChecked();
                        // Xử lý lọc dữ liệu tương ứng với checkbox
                        filterProducts();
                    }
                });

                checkBox100kTo250k.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        is100kTo250kChecked = checkBox100kTo250k.isChecked();
                        filterProducts();
                    }
                });

                checkBox250kTo400k.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        is250kTo400kChecked = checkBox250kTo400k.isChecked();
                        filterProducts();
                    }
                });

                checkBoxOver400k.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isOver400kChecked = checkBoxOver400k.isChecked();
                        filterProducts();
                    }
                });
            }
        });

    }

    // Function to filter products based on all selected filters
    private void filterProducts() {
        filteredCatalog.clear();
        if (!isUnder100kChecked && !is100kTo250kChecked && !is250kTo400kChecked && !isOver400kChecked) {
            // If no filters are selected, display the original catalog
            filteredCatalog.addAll(originalCatalog);
        } else {
            // Apply filters based on checkbox states
            for (Product product : originalCatalog) {
                double price = product.getProductPrice();
                if ((isUnder100kChecked && price < 100000) ||
                        (is100kTo250kChecked && price >= 100000 && price <= 250000) ||
                        (is250kTo400kChecked && price > 250000 && price <= 400000) ||
                        (isOver400kChecked && price > 400000)) {
                    filteredCatalog.add(product);
                }
            }
        }
        catalogAdapter.updateData(filteredCatalog);
    }
    private void resetCatalog() {
        catalog.clear();
        catalog.addAll(originalCatalog);
        catalogAdapter.notifyDataSetChanged();
    }


}




