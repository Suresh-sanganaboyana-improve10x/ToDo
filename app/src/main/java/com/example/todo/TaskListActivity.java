package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {
    public ArrayList<TaskList> taskLists;
    public RecyclerView taskListRv;
    public TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().setTitle("Task List");
        setupDataForTaskList();
        setupAddButton();
        setupRecyclerViewForTaskList();
    }
    public void setupDataForTaskList() {
        taskLists = new ArrayList<>();
        TaskList one = new TaskList();
        one.taskTxt = "Get vegetables";
        one.description = "for 1 week";
        taskLists.add(one);

        TaskList two = new TaskList();
        two.taskTxt = "Reading news";
        two.description = "Explore politcs, filmy and sports";
        taskLists.add(two);

        TaskList three = new TaskList();
        three.taskTxt = "Prepare Lunch";
        three.description = "Biryani and Raitha. yummyyyyy";
        taskLists.add(three);

        TaskList four = new TaskList();
        four.taskTxt = "Have Breakfast0";
        four.description = "Healthy breakfast for a better morning";
        taskLists.add(four);
    }

    public void setupRecyclerViewForTaskList() {
        taskListRv = findViewById(R.id.task_list_rv);
        taskListRv.setLayoutManager(new LinearLayoutManager(this));
        taskListAdapter = new TaskListAdapter();
        taskListAdapter.setupData(taskLists);
        taskListRv.setAdapter(taskListAdapter);
    }

    public  void setupAddButton() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
        });
    }
}