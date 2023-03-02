package com.example.data

import android.database.Observable
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.domain.model.TripPlan
//import com.example.graphql.AllTripPlansQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(
    baseUrl: String,
//    apiHeaderInterceptor: ApiHeaderInterceptor
) {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl(baseUrl)
        .okHttpClient(
            OkHttpClient.Builder()
//                .addInterceptor(apiHeaderInterceptor)
                .build()
        )
        .build()

    fun getAllTripPlans() { //: Observable<List<TripPlan>> {
        GlobalScope.launch(Dispatchers.Main) {
//            apolloClient.query(AllTripPlansQuery()).toFlow().collect {
//                Log.d("DAYUN: GET ALL TRIP PLANS: ", "${it.data?.tripPlans}")
//            }
        }

//        return apolloClient.query(AllTripPlansQuery())
//            .rx()
//            .doOnNext(::handleError)
//            .map { item ->
//                val list = mutableListOf<StoryDto>()
//                item.data!!.storyList.list.forEach {
//                    list.add(it.fragments.storyDto)
//                }
////                val list = mutableListOf<OnStageStory>()
////                item.data!!.storyList.list.forEach {
////                    list.add(
////                        OnStageStory(
////                            id = it.fragments.storyDto.storyId,
////                            title = it.fragments.storyDto.name,
////                            shortDesc = it.fragments.storyDto.shortDesc ?: "",
////                            isFinished = it.fragments.storyDto.isFinished,
////                            imageUrl = it.fragments.storyDto.mainImageFile?.link ?: "",
////                            wideImageUrl = it.fragments.storyDto.wideImageFile?.link ?: "",
////                            introImageUrl = it.fragments.storyDto.introImageFile?.link ?: ""
////                        )
////                    )
////                }
//
//                list
//            }
    }

//    private fun handleError(response: Response<*>) {
//        if (response.hasErrors()) {
//            Log.e("Api service handle error", response.errors?.first()?.message ?: "")
//        }
//    }
}