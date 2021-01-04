package com.yichun.listviewdemo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView listView1;
    private ArrayAdapter<String> listAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = (ListView) findViewById(R.id.listView1);
        // add multiple colors to a string array
        String[] someColors = new String[] { "Red", "Orange", "Yellow",
                "Green", "Blue", "Indigo", "Violet", "Black", "White"};
        ArrayList<String> colorArrayList = new ArrayList<String>();
        //add string array elements into an arraylist
        colorArrayList.addAll( Arrays.asList(someColors) );
        //use simple_list_item_checked template in listview
        listAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,
                colorArrayList);

        listView1.setAdapter( listAdapter1 );

        listView1.setChoiceMode(listView1.CHOICE_MODE_MULTIPLE);
        // display a message when click on an item
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listView1.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,"You selected color is " +itemValue, Toast.LENGTH_SHORT).show();

            }
        });

    }
    // display message when click on SUBMIT SELECTION button
    public void doClick(View view) {
        int count=listView1.getCount();
        ArrayList<String> colorSelection = new ArrayList<String>();
        String str = "";
        SparseBooleanArray viewItems = listView1.getCheckedItemPositions();
        for(int i=0; i<count; i++) {
            if(viewItems.get(i)) {
                String selectedColor = (String) listView1.getItemAtPosition(i);
                colorSelection.add(selectedColor);
            }
        }
        // add string elements in colorSelection into a single string to avoid brackets in the message
        for (String color : colorSelection){
            str  = " "+ color + str;
        }
        //display message
        Toast.makeText(MainActivity.this,"The color selection is"+str,Toast.LENGTH_SHORT).show();


    }
}