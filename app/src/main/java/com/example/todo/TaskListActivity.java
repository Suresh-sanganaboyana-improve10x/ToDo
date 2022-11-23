package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskListActivity extends AppCompatActivity {
    public ArrayList<TaskList> taskLists = new ArrayList<>();
    public RecyclerView taskListRv;
    public TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().setTitle("Task List");
        setupAddButton();
        setupRecyclerViewForTaskList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    public void fetchData() {
        TodoApi todoApi = new TodoApi();
        TodoService todoService = todoApi.createTodoService();
        Call<List<TaskList>> call = todoService.fetchTask();
        call.enqueue(new Callback<List<TaskList>>() {
            @Override
            public void onResponse(Call<List<TaskList>> call, Response<List<TaskList>> response) {
                List<TaskList> taskLists= response.body();
                taskListAdapter.setupData(taskLists);
            }

            @Override
            public void onFailure(Call<List<TaskList>> call, Throwable t) {
                Toast.makeText(TaskListActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupRecyclerViewForTaskList() {
        taskListRv = findViewById(R.id.task_list_rv);
        taskListRv.setLayoutManager(new LinearLayoutManager(this));
        taskListAdapter = new TaskListAdapter();
        taskListAdapter.setupData(taskLists);
        taskListAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteTask(id);
            }

            @Override
            public void onEdit(TaskList taskList) {

            }
        });
        taskListRv.setAdapter(taskListAdapter);
    }

    public void deleteTask(String id) {
        TodoApi todoApi = new TodoApi();
        TodoService todoService = todoApi.createTodoService();
        Call<Void> call = todoService.deleteTask(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TaskListActivity.this, "Successfully delete the task", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TaskListActivity.this, "Failed to delete task", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void setupAddButton() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
        });
    }
}