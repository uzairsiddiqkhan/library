package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.booklibrary.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<String> bookId, bookTitle, bookAuthor, bookPages;
    myHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addACtivity.class);
                startActivityForResult(intent, 1);
            }


        });

        // now creating instancce of those global variables defined above

        helper = new myHelper(this);

        bookId = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookPages = new ArrayList<>();

        customAdapter adapter = new customAdapter(MainActivity.this,this, bookId, bookTitle, bookAuthor, bookPages);
        binding.recyclarView.setAdapter(adapter);
        binding.recyclarView.setLayoutManager(new LinearLayoutManager(this));


        data();

    }

    void data() {
        Cursor cursor = helper.resultData();


        if (cursor.getCount() == 0) {

            binding.imgerror.setVisibility(View.VISIBLE);
            binding.tverror.setVisibility(View.VISIBLE);


        } else {
            while (cursor.moveToNext()) {
                bookId.add(cursor.getString(0));

                bookTitle.add(cursor.getString(1));

                bookAuthor.add(cursor.getString(2));
                bookPages.add(cursor.getString(3));


            }
            binding.imgerror.setVisibility(View.GONE);
            binding.tverror.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all){
         alertdialogue();
        }
        return super.onOptionsItemSelected(item);
    }
    void alertdialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletion ALL Library" );
        builder.setMessage("Are you sure you want to delete Whole Library");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Deleted all", Toast.LENGTH_SHORT).show();
                myHelper helper = new myHelper(MainActivity.this);
                helper.deleteaAll();
//            recreate(); // while recreating we get a block sccreen which is not a good experience
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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