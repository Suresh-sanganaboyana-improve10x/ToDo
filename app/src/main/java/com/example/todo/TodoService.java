package com.example.todo;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TodoService{

    @GET("sureshTodo")
    Call<List<TaskList>> fetchTask();

    @POST("sureshTodo")
    Call<TaskList> createTask(@Body TaskList taskList);

    @DELETE("sureshTodo/{id}")
    Call<Void> deleteTask(@Path("id") String id);

    @PUT("sureshTodo/{id}")
    Call<Void> updateTask(@Path("id") String id, @Body TaskList taskList);

}
