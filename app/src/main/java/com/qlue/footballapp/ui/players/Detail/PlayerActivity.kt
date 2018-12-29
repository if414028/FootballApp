package com.qlue.footballapp.ui.players.Detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qlue.footballapp.R
import com.qlue.footballapp.model.Player
import com.qlue.footballapp.model.repo.PlayerRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import com.qlue.footballapp.utils.SchedulerProviderImpl
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.view_detail_player.*

class PlayerActivity : AppCompatActivity(), PlayerDetailMvpView {

    lateinit var player: Player
    lateinit var mPresenter: PlayerDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        player = intent.getParcelableExtra("player")
        supportActionBar?.title = player.strPlayer
        val service = FootballService.getClient().create(APIFootball::class.java)
        val request = PlayerRepoImpl(service)
        val scheduler = SchedulerProviderImpl()
        mPresenter = PlayerDetailPresenter(this, request, scheduler)
        mPresenter.getPlayerData(player.idPlayer)
    }

    override fun displayPlayerDetail(player: Player) {
        initView(player)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    private fun initView(player: Player) {
        loadBanner(player)
        Glide.with(applicationContext)
            .load(player.strCutout)
            .apply(RequestOptions().placeholder(R.drawable.ic_action_load))
            .into(imgPlayer)


        playerName.text = player.strPlayer
        tvPosition.text = player.strPosition
        tvDate.text = player.dateBorn
        tvHeight.text = player.strHeight
        tvWeight.text = player.strWeight
        playerOverview.text = player.strDescriptionEN
    }

    private fun loadBanner(player: Player) {
        if (!player.strFanart1.equals(null)) {
            Glide.with(applicationContext)
                .load(player.strFanart1)
                .apply(RequestOptions().placeholder(R.drawable.ic_action_load))
                .into(imageBannerPlayer)
        } else {
            Glide.with(applicationContext)
                .load(player.strThumb)
                .apply(RequestOptions().placeholder(R.drawable.ic_action_load))
                .into(imageBannerPlayer)
        }
    }
}
