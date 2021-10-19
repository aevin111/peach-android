package com.example.peach.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

import com.example.peach.models.APIStatusModel;
import com.example.peach.models.ClassificationsModel;
import com.example.peach.models.CreateCurrentCropModel;
import com.example.peach.models.CreateNewCropModel;
import com.example.peach.models.CropsListModel;
import com.example.peach.models.CurrentCropModel;
import com.example.peach.models.CurrentTaskModel;
import com.example.peach.models.DeleteCropModel;
import com.example.peach.models.DeleteCurrentCropModel;
import com.example.peach.models.FieldCapacityModel;
import com.example.peach.models.PlantsSearchResultModel;
import com.example.peach.models.SensorStatusModel;
import com.example.peach.models.SummarizedPlantsListModel;
import com.example.peach.models.SymptomModel;

public interface API
{
    @GET("api_status")
    Call<APIStatusModel> getStatus(@Header("password") String password);
    @GET("current_crop")
    Call<CurrentCropModel> getCurrentCrop();
    @GET("plant?action=get_summarized_plants_list")
    Call<List<SummarizedPlantsListModel>> getSummarizedPlantsList();
    @POST("crop")
    Call<CreateNewCropModel> createCrop(@Header("plant_id") Integer plantId, @Header("name") String name);
    @POST("current_crop")
    Call<CreateCurrentCropModel> createCurrentCrop(@Header("crop_id") Integer cropId);
    @DELETE("current_crop")
    Call<DeleteCurrentCropModel> deleteCurrentCrop();
    @DELETE("crop")
    Call<DeleteCropModel> deleteCrop(@Header("crop_id") Integer cropId);
    @GET("plant?action=search_for_plant")
    Call<List<PlantsSearchResultModel>> searchForPlant(@Query("search_string") String searchString);
    @GET("current_task")
    Call<List<CurrentTaskModel>> getCurrentTasks(@Query("date") String date);
    @GET("sensor_status")
    Call<SensorStatusModel> getSensorStatus();
    @GET("crop?action=get_all_crops")
    Call<List<CropsListModel>> getAllCrops();
    @GET("diseases?action=get_symptoms")
    Call<List<SymptomModel>> getSymptoms(@Query("plant_id") Integer plantId);
    @GET("diseases?action=get_disease")
    Call<ClassificationsModel> getDisease(@Query("plant_id") Integer plantId, @Query("symptoms") String symptoms);
    @PUT("sensor?action=set_field_capacity")
    Call<FieldCapacityModel> setFieldCapacity(@Header("field_capacity") Integer fieldCapacity);
}
