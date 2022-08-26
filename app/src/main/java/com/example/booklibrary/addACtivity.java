package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.booklibrary.databinding.ActivityAddBinding;

public class addACtivity extends AppCompatActivity {

    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHelper helper = new myHelper(addACtivity.this);

                String bookName = binding.etBook.getText().toString().trim();
                String authorName = binding.etAuthor.getText().toString().trim();
                String bookPages = binding.etPages.getText().toString().trim();
                helper.addBook(bookName, authorName, bookPages);
                finish(); // to come back to main activity
            }

        });
    }
}