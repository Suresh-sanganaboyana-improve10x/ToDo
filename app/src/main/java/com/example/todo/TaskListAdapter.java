package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListViewHolder> {

    public List<TaskList> taskListArrayList;
    public OnItemActionListener onItemActionListener;
    public void setupData(List<TaskList> taskLists) {
        taskListArrayList = taskLists;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }
    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        TaskListViewHolder taskListViewHolder = new TaskListViewHolder(view);
        return taskListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {
        TaskList taskList = taskListArrayList.get(position);
        holder.taskTxt.setText(taskList.taskTxt);
        holder.descriptionTxt.setText(taskList.description);
        holder.deleteImgBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(taskList.id);
        });

    }

    @Override
    public int getItemCount() {
        return taskListArrayList.size();
    }
}
