package com.kevinbigler.mariomakertracker.service;

import com.kevinbigler.mariomakertracker.common.MMUtils;
import com.kevinbigler.mariomakertracker.entity.Player;
import com.kevinbigler.mariomakertracker.entity.repository.PlayerRepository;
import com.kevinbigler.mariomakertracker.pojo.PlayerPreviewPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by Kevin on 3/27/2017.
 */
@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Player savePlayer(PlayerPreviewPojo playerPreviewPojo) {
        if (playerPreviewPojo == null) {
            throw new IllegalArgumentException("playerPreviewPojo is NULL");
        } else if (MMUtils.isPlayerNintendoIdValid(playerPreviewPojo.getNintendoId())) {
            throw new IllegalArgumentException("playerPreviewPojo's nintendoId is invalid");
        }

        Player player = playerRepository.findByNintendoId(playerPreviewPojo.getNintendoId());
        if (player == null) {
            player = new Player();
            player.setNintendoId(playerPreviewPojo.getNintendoId());
            player.setCreated(new Timestamp(System.currentTimeMillis()));
        }

        BeanUtils.copyProperties(playerPreviewPojo, player);
        player.setUpdated(new Timestamp(System.currentTimeMillis()));

        return player;
    }
}
