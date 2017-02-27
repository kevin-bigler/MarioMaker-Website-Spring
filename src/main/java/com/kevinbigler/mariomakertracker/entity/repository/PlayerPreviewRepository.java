package com.kevinbigler.mariomakertracker.entity.repository;

import com.kevinbigler.mariomakertracker.entity.Player;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kevin on 2/25/2017.
 */
public interface PlayerPreviewRepository extends CrudRepository<Player, Long> {
}
