package com.example.nassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private List<Subject> subjects;
    private ListView listView;
    private Spinner spinner;
    private ArrayAdapter<String> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initializeSubjects();
        spinner = findViewById(R.id.spinner);
        Button showItemsButton = findViewById(R.id.button1);
        listView = findViewById(R.id.listView);
        List<String> subjectNames = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectNames.add(subject.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjectNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        showItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedSubject = spinner.getSelectedItem().toString();
                List<Item> items = getItemsForSubject(selectedSubject);
                List<String> itemNames = new ArrayList<>();
                for (Item item : items) {
                    itemNames.add(item.getName());
                }
                itemAdapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, itemNames);
                listView.setAdapter(itemAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Item selectedItem = items.get(position);
                        Intent intent;
                        if ("Apple".equals(selectedItem.getName())) {
                            intent = new Intent(MainActivity2.this, ItemMainActivity.class);
                        } else if ("Orange".equals(selectedItem.getName())) {
                            intent = new Intent(MainActivity2.this, ItemMainActivity2.class);
                        } else if("Carrot".equals(selectedItem.getName())){
                            intent = new Intent(MainActivity2.this, ItemMainActivity3.class);
                            intent.putExtra("itemName", selectedItem.getName());
                        }
                        else if("Broccoli".equals(selectedItem.getName())){
                            intent = new Intent(MainActivity2.this, ItemMainActivity4.class);
                            intent.putExtra("itemName", selectedItem.getName());
                        }
                        else if("Chicken Breast".equals(selectedItem.getName())){
                            intent = new Intent(MainActivity2.this, ItemMainActivity5.class);
                            intent.putExtra("itemName", selectedItem.getName());
                        }
                        else if("Egg".equals(selectedItem.getName())){
                            intent = new Intent(MainActivity2.this, ItemMainActivity6.class);
                            intent.putExtra("itemName", selectedItem.getName());
                        }
                        else {
                            intent = new Intent(MainActivity2.this, ItemMainActivity.class);
                            intent.putExtra("itemName", selectedItem.getName());
                        }
                        startActivity(intent);
                    }
                });
            }
        });
    }
    private void initializeSubjects() {
        subjects = new ArrayList<>();
        subjects.add(new Subject("Fruits", Arrays.asList(
                new Item("Apple"),
                new Item("Orange")
        )));
        subjects.add(new Subject("Vegetables", Arrays.asList(
                new Item("Carrot"),
                new Item("Broccoli")
        )));
        subjects.add(new Subject("Proteins", Arrays.asList(
                new Item("Chicken Breast"),
                new Item("Egg")
        )));
    }
    private List<Item> getItemsForSubject(String subjectName) {
        for (Subject subject : subjects) {
            if (subject.getName().equals(subjectName)) {
                return subject.getItems();
            }
        }
        return new ArrayList<>();
    }
}
