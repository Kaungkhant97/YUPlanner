package com.kaungkhantthu.yuplanner.data.api;




import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.EventResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by hhtet on 3/17/16.
 */
public interface RetrofitService {

  @GET(APIConfig.EVENT_LIST)
  Call<EventResponse> getEventList();

  @POST(APIConfig.EVENT_LIST)
  Call<Event> postEvent(@Body Event e);







}
