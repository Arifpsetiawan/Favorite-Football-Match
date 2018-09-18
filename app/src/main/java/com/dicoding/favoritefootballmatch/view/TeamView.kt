package com.dicoding.favoritefootballmatch.view

import com.dicoding.favoritefootballmatch.other.Team

interface TeamView : BaseView{
    fun showTeamList(teams: List<Team>)
}