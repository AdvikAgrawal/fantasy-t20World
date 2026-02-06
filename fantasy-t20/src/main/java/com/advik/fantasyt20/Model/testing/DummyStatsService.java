package com.advik.fantasyt20.Model.testing;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.advik.fantasyt20.Model.PlayerStat;
import com.advik.fantasyt20.Model.StatProvider;

@Service
public class DummyStatsService implements StatProvider{
    private final Random random = new Random();

    @Override
    public List<PlayerStat> getMatchStats(Long matchId) {
            return List.of(
            new PlayerStat(1L, random.nextInt(80), random.nextInt(3), random.nextInt(2)),
            new PlayerStat(2L, random.nextInt(50), random.nextInt(4), random.nextInt(3)),
            new PlayerStat(3L, random.nextInt(100), 0, random.nextInt(2)),
            new PlayerStat(4L, random.nextInt(30), random.nextInt(5), 0),
            new PlayerStat(5L, random.nextInt(60), random.nextInt(2), random.nextInt(4))
        );

    }
}
