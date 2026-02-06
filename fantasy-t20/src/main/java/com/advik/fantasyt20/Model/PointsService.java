package com.advik.fantasyt20.Model;

import java.util.List;


import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PointsService {
    
    private final PlayersPointsRepository playersPointsRepository;
    private final PlayersRepository playersRepository;
    private final StatProvider statProvider;

    @Transactional
    public void processTestRun() {
        calculatePointsFromStats(-1L);
    }
    public void calculatePointsFromStats(Long matchId) {
        List<PlayerStat> stats = statProvider.getMatchStats(matchId);
        for (PlayerStat stat : stats) {

            Players player = playersRepository.findById(stat.getPlayerId()).get();

            int points = calculateTotalPoints(stat);

            PlayersPoints pp = new PlayersPoints();
            pp.setPlayers(player);
            pp.setMatchId(matchId);
            pp.setPoints(points);

            playersPointsRepository.save(pp);
        }
    }
    private int calculateRunPoints(int runs) {
        if (runs == 0) return -1;
        else if (runs >= 20 && runs <= 30) return 1;
        else if (runs > 30 && runs <= 50) return 2;
        else if (runs > 50 && runs <= 100) return 3;
        else if (runs > 100) return 5;
        return 0; // default case
    }
    private int calculateWicketPoints(int wickets) {
    if (wickets == 1) return 1;
    else if (wickets == 2) return 2;
    else if (wickets == 3) return 3;
    else if (wickets >= 4) return 5;
    return 0;
    }
    private int calculateCatchPoints(int catches) {
    return catches * 1; // 1 point for first catch, ignore extra
    }
    private int calculateTotalPoints(PlayerStat stat) {
        return calculateRunPoints(stat.getRuns())
            + calculateWicketPoints(stat.getWickets())
            + calculateCatchPoints(stat.getCatches());
    }
}
