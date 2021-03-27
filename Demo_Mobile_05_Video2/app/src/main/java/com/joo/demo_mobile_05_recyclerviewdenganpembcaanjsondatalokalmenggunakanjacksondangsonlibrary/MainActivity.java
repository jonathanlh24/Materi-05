package com.joo.demo_mobile_05_recyclerviewdenganpembcaanjsondatalokalmenggunakanjacksondangsonlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.joo.demo_mobile_05_recyclerviewdenganpembcaanjsondatalokalmenggunakanjacksondangsonlibrary.adapter.StudentAdapter;
import com.joo.demo_mobile_05_recyclerviewdenganpembcaanjsondatalokalmenggunakanjacksondangsonlibrary.databinding.ActivityMainBinding;
import com.joo.demo_mobile_05_recyclerviewdenganpembcaanjsondatalokalmenggunakanjacksondangsonlibrary.entity.Student;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<Student> students = new ArrayList<>();
        try {
//            students.addAll(fetchDataWithJackson());
            students.addAll(fetchDataWithGson());
        } catch (IOException e) {
            e.printStackTrace();
        }
        StudentAdapter studentAdapter = new StudentAdapter(students, student -> Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.rvStudent.setLayoutManager(manager);
        binding.rvStudent.setAdapter(studentAdapter);
    }


    private List<Student> fetchDataWithJackson() throws IOException {
        List<Student> students;
        try (InputStream inputStream = getAssets().open("students.json")) {
            ObjectMapper mapper = new ObjectMapper();
            students = Arrays.asList(mapper.readValue(inputStream, Student[].class));
        }
        return students;
    }

    private List<Student> fetchDataWithGson() throws IOException {
        List<Student> students;
        try (InputStream inputStream = getAssets().open("students.json")) {
            try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"))) {
                Gson gson = new Gson();
                students = Arrays.asList(gson.fromJson(reader, Student[].class));
            }
        }
        return students;
    }
}