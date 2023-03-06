package com.example.data.mapper

import com.example.domain.model.*
import com.example.graphql.fragment.DayPlanDto
import com.example.graphql.fragment.TripPlanDto
import com.example.graphql.fragment.UserDto
import com.example.graphql.type.State
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun stateMapper(state: State): STATE {
    return when (state) {
        State.PREPARING -> {
            STATE.PREPARING
        }
        State.TRAVELING -> {
            STATE.TRAVELING
        }
        else -> {
            STATE.DONE
        }
    }
}

fun destinationMapper(destination: TripPlanDto.Destination): com.example.domain.model.Destination {
    return com.example.domain.model.Destination(
        id = destination.id,
        name = destination.name,
        imageUrl = destination.imageUrl ?: ""
    )
}

fun DtoToTripPlanMapper(tripPlanDto: TripPlanDto): TripPlan {
    return TripPlan(
        id = tripPlanDto.id,
        title = tripPlanDto.title,
        state = stateMapper(tripPlanDto.state),
        participants = tripPlanDto.participantIds.map {
            DtoToUserMapper(it.userDto)
        },
        destination = destinationMapper(tripPlanDto.destination),
        startDate = LocalDateTime.parse(tripPlanDto.startDate.toString()),
        endDate = LocalDateTime.parse(tripPlanDto.endDate.toString())
    )
}

fun DtoToUserMapper(userDto: UserDto): User {
    return User(
        id = userDto.id,
        name = userDto.name,
        tripPlanIds = userDto.tripPlanIds
    )
}

fun DtoToDayPlanMapper(dayPlanDto: DayPlanDto): DayPlan {
    return DayPlan(
        id = dayPlanDto.id,
        date = LocalDateTime.parse(dayPlanDto.date.toString(), DateTimeFormatter.ISO_LOCAL_DATE),
        day = dayPlanDto.day,
        schedules = dayPlanDto.scheduleIds.map {
            Schedule(
                id = it.id,
                cost = it.cost,
                memo = it.memo ?: "",
                startTime = LocalDateTime.parse(it.startTime.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                endTime = LocalDateTime.parse(it.endTime.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                place = Place(
                    name = it.place.name,
                    address = it.place.address
                )
            )
        }
    )
}