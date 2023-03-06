package com.example.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.graphql.AllTripPlansQuery
import com.example.graphql.CreateNewTripPlanMutation
import com.example.graphql.DayPlansQuery
import com.example.graphql.adapter.AllTripPlansQuery_ResponseAdapter
import com.example.graphql.fragment.TripPlanDto
import com.example.graphql.selections.AllTripPlansQuerySelections
import com.example.graphql.type.CreateTripPlanInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import java.time.LocalDateTime
import java.util.*
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

    fun createTripPlan(title: String, destinationId: String, participantId: String, startDate: LocalDateTime, totalDay: Int) : Flow<Unit> {
        return apolloClient.mutation(CreateNewTripPlanMutation(input = CreateTripPlanInput(
            title = title,
            destinationId = destinationId,
            participantId = participantId,
            startDate = startDate,
            totalDay = totalDay
        ))).toFlow().map {  }
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
    }
}