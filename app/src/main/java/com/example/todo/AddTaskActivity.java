package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setTitle("Add Task");
        handleAddBtn();
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
        Button taskAddBtn = findViewById(R.id.task_add_btn);
        taskAddBtn.setOnClickListener(view -> {
            EditText taskTxt = findViewById(R.id.task_txt);
            String task = taskTxt.getText().toString();
            EditText taskDescription = findViewById(R.id.task_description_txt);
            String description = taskDescription.getText().toString();
            addTask(task,description);
        });
    }
}