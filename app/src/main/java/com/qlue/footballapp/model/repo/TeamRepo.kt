package com.qlue.footballapp.model.repo

import com.qlue.footballapp.model.Teams
import io.reactivex.Flowable

interface TeamRepo {

    fun getTeam(id: String = "0"): Flowable<Teams>

    fun getDetailTeam(id: String = "0"): Flowable<Teams>

    fun getTeamBySearch(query: String): Flowable<Teams>

    fun getAllTeam(id: String): Flowable<Teams>
}