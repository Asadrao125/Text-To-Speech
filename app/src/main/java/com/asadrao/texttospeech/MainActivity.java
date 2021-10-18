package com.asadrao.texttospeech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edtCategory;
    Button btnAddCategory;
    RecyclerView rvCategories;
    ArrayList<CategoryModel> stringArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCategory = findViewById(R.id.edtCategory);
        btnAddCategory = findViewById(R.id.btnAddCategory);
        rvCategories = findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));
        rvCategories.setHasFixedSize(true);

        stringArrayList.add(new CategoryModel(0, R.drawable.heart, "میری کیفیت"));
        stringArrayList.add(new CategoryModel(0, R.drawable.need, "مجھے چاہیے"));
        stringArrayList.add(new CategoryModel(0, R.drawable.call_him, "اسے بلاؤ"));
        stringArrayList.add(new CategoryModel(0, R.drawable.clean, "صاف کرو"));
        stringArrayList.add(new CategoryModel(0, R.drawable.pain, "مجھے درد ہے"));
        stringArrayList.add(new CategoryModel(0, R.drawable.call_him, "بات چیت"));
        stringArrayList.add(new CategoryModel(0, R.drawable.write, "بتانے کے لیے لکھیں"));
        rvCategories.setAdapter(new CategoriesAdapter(this, stringArrayList));

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtCategory.getText().toString().trim();
                if (!text.isEmpty()) {
                    stringArrayList.add(new CategoryModel(1, R.drawable.write, text));
                    rvCategories.setAdapter(new CategoriesAdapter(getApplicationContext(), stringArrayList));
                }
            }
        });
    }
}