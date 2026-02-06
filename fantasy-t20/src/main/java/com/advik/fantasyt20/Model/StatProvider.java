package com.advik.fantasyt20.Model;

import java.util.List;

public interface StatProvider {
    List<PlayerStat> getMatchStats(Long matchId);
}
