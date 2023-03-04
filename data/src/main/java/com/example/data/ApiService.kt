package com.example.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.graphql.AllTripPlansQuery
import com.example.graphql.DayPlansQuery
import com.example.graphql.adapter.AllTripPlansQuery_ResponseAdapter
import com.example.graphql.fragment.TripPlanDto
import com.example.graphql.selections.AllTripPlansQuerySelections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(
    private val baseUrl: String,
//    apiHeaderInterceptor: ApiHeaderInterceptor
) {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl(baseUrl)
        .build()
//        .okHttpClient(
//            OkHttpClient.Builder()
//                .addInterceptor(apiHeaderInterceptor)
//                .build()
//        )

    fun getDayPlans(tripPlanId: String) {
        val flow = apolloClient.query(DayPlansQuery(tripPlanId))
            .toFlow()
    }

    fun getAllTripPlans() : Flow<List<TripPlanDto>> {
        val flow = apolloClient.query(AllTripPlansQuery())
            .toFlow()
            .map { item ->
            val tripPlanList = mutableListOf<TripPlanDto>()
            item.data!!.tripPlans?.forEach {
                if (it != null) {
                    tripPlanList.add(it.tripPlanDto)
                }
            }
            tripPlanList
        }

        return flow
//        GlobalScope.launch(Dispatchers.IO) {
//            flow.collect {
//                Log.d("DAYUN: COLLECT!", it.data?.tripPlans?.get(0).toString())
//            }
//        }

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