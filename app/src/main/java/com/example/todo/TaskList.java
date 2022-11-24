package com.example.todo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaskList implements Serializable {
    @SerializedName("_id")
    public String id;
    @SerializedName("task")
    public String taskTxt;
    public String description;
}
