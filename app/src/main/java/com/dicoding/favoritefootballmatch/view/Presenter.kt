package com.dicoding.favoritefootballmatch.view

import com.google.gson.Gson
import com.dicoding.favoritefootballmatch.api.ApiRepository
import com.dicoding.favoritefootballmatch.api.TheSportDBApi
import com.dicoding.favoritefootballmatch.other.Event
import com.dicoding.favoritefootballmatch.other.EventResponse
import com.dicoding.favoritefootballmatch.other.Team
import com.dicoding.favoritefootballmatch.other.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Presenter<in T>(private val view: T) {
    private val repository = ApiRepository()
    private val gson = Gson()

    fun getDetailMatchEvent(idMatchEvent: String?) {
        if (view is EventDetailView) {
            view.showLoading()
            doAsync {
                val data: Event? = gson.fromJson(repository.doRequest(TheSportDBApi.getDetailMatchEvent(idMatchEvent)),
                        EventResponse::class.java).events[0]

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showEventDetail(it1) }
                }
            }
        }
    }

    fun getNextMatchEvent(idLeague: String?) {
        if (view is EventView) {
            view.showLoading()
            doAsync {
                val data: List<Event>? = gson.fromJson(repository.doRequest(TheSportDBApi.getNextMatchEvent(idLeague)),
                        EventResponse::class.java).events

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showEventList(it1) }
                }
            }
        }
    }

    fun getPastMatchEvent(idLeague: String?) {
        if (view is EventView) {
            view.showLoading()
            doAsync {
                val data: List<Event>? = gson.fromJson(repository.doRequest(TheSportDBApi.getPastMatchEvent(idLeague)),
                        EventResponse::class.java).events

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showEventList(it1) }
                }
            }
        }
    }

    fun getSpecificTeam(searchInput: String?) {
        if (view is TeamDetailView) {
            view.showLoading()
            doAsync {
                val data: Team? = gson.fromJson(repository.doRequest(TheSportDBApi.getSpecificTeam(searchInput)),
                        TeamResponse::class.java).teams[0]

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showTeamDetail(it1) }

                }
            }
        }
        if (view is EventDetailView) {
            view.showLoading()
            doAsync {
                val data: Team? = gson.fromJson(repository.doRequest(TheSportDBApi.getSpecificTeam(searchInput)),
                        TeamResponse::class.java).teams[0]

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showTeamEmblem(it1) }

                }
            }
        }

    }
}