package com.advik.fantasyt20.Model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final UserTeamPlayersRepository userTeamPlayersRepository;
    public List<Leaderboard> getLeaderboard() {
        List<Object[]> board = userTeamPlayersRepository.getLeaderBoardRaw();

        return board.stream().map(row -> new Leaderboard((String) row[0], ((Number) row[1]).intValue())).toList();
    }
}