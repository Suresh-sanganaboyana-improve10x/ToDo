package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskActivity extends AppCompatActivity {
    public TaskList taskList;
    public EditText taskTxt;
    public EditText taskDescriptionTxt;
    public Button taskAddBtn;
    public Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskTxt = findViewById(R.id.task_txt);
        taskDescriptionTxt = findViewById(R.id.task_description_txt);
        taskAddBtn = findViewById(R.id.task_add_btn);
        updateBtn = findViewById(R.id.update_btn);
        if (getIntent().hasExtra("task")) {
            getSupportActionBar().setTitle("Edit Task");
            taskList = (TaskList) getIntent().getSerializableExtra("task");
            updateBtn.setVisibility(View.VISIBLE);
            taskAddBtn.setVisibility(View.GONE);
            showData();
            handleUpdateBtn();

        } else {
            getSupportActionBar().setTitle("Add Task");
            taskAddBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.GONE);
            handleAddBtn();
        }
    }

    public void handleUpdateBtn() {
        updateBtn.setOnClickListener(view -> {
            String taskName = taskTxt.getText().toString();
            String description = taskDescriptionTxt.getText().toString();
            updateTask(taskList.id, taskName, description);
        });
    }

    public void updateTask(String id, String taskName, String taskDescription) {
        TaskList taskList = new TaskList();
        taskList.taskTxt = taskName;
        taskList.description = taskDescription;

        TodoApi todoApi = new TodoApi();
        TodoService todoService = todoApi.createTodoService();
        Call<Void> call = todoService.updateTask(id, taskList);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddTaskActivity.this, "Successfully updated task", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddTaskActivity.this, "Failed to update task", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void showData() {
        taskTxt.setText(taskList.taskTxt);
        taskDescriptionTxt.setText(taskList.description);
    }

    public void addTask(String taskName, String description) {
        TaskList taskList = new TaskList();
        taskList.taskTxt = taskName;
        taskList.description = description;

        TodoApi todoApi = new TodoApi();
        TodoService todoService = todoApi.createTodoService();
        Call<TaskList> call = todoService.createTask(taskList);
        call.enqueue(new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                finish();
            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {
                Toast.makeText(AddTaskActivity.this, "Failed fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleAddBtn() {
        taskAddBtn = findViewById(R.id.task_add_btn);
        taskAddBtn.setOnClickListener(view -> {
            taskTxt = findViewById(R.id.task_txt);
            String task = taskTxt.getText().toString();
            taskDescriptionTxt = findViewById(R.id.task_description_txt);
            String description = taskDescriptionTxt.getText().toString();
            addTask(task, description);
        });
    }
}