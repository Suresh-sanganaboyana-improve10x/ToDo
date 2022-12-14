package com.example.todo;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListViewHolder extends RecyclerView.ViewHolder {
    TextView taskTxt;
    TextView descriptionTxt;
    ImageButton deleteImgBtn;
    public TaskListViewHolder(@NonNull View itemView) {
        super(itemView);
        taskTxt = itemView.findViewById(R.id.task_txt);
        descriptionTxt = itemView.findViewById(R.id.description_txt);
        deleteImgBtn = itemView.findViewById(R.id.delete_img_btn);
    }
}
