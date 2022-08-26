package com.example.booklibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.booklibrary.databinding.ActivityUpdateBinding;

public class updateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    String id;
    String book, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentData();

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myHelper mh = new myHelper(updateActivity.this);
                String newPages = binding.etPages.getText().toString().trim();
                String newAuthor = binding.etAuthor.getText().toString().trim();
                String newTitle = binding.etBook.getText().toString().trim();
                mh.updateData(id, newTitle, newAuthor, newPages);
                // after updation setting title dynamically
                // if fixed than go to manifest add label
                getSupportActionBar().setTitle(newTitle);
                finish(); // to come back to main Activity.
            }
        });

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myHelper m = new myHelper(updateActivity.this);
//                m.rowDeletion(id);
              alertdialogue();

            }
        });

        // before updation setting title dynamically
        // if fixed than go to manifest add label
        getSupportActionBar().setTitle(book);


    }

    void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {


            book = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            id = getIntent().getStringExtra("id");

            binding.etPages.setText(pages);
            binding.etAuthor.setText(author);
            binding.etBook.setText(book);

        } else
            Toast.makeText(this, " probelmm  ", Toast.LENGTH_SHORT).show();
    }

    void alertdialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletion of Book" + book);
        builder.setMessage("Are you sure you want to delete book " + book + " from library");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                myHelper m = new myHelper(updateActivity.this);
                m.rowDeletion(id);
                recreate();
                finish(); // to dynamically close the activity

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}