package com.example.todo;

import com.google.gson.annotations.SerializedName;

public class TaskList {
    @SerializedName("_id")
    public String id;
    @SerializedName("task")
    public String taskTxt;
    public String description;
}
