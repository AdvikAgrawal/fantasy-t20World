package com.advik.fantasyt20.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface UserTeamPlayersRepository extends JpaRepository<UserTeamPlayers, Long>{
    @Modifying
    @Transactional
    void deleteByMyAppUserEmail(String email);

    List<UserTeamPlayers> findByMyAppUserEmail(String email);

    @Query(value = """
        SELECT u.email AS user_email,
            SUM(pp.points) AS total_points
        FROM user_team_players utp
        JOIN my_app_user u ON utp.user_email = u.email
        JOIN players p ON utp.player_id = p.id
        JOIN players_points pp ON pp.player_id = p.id
        GROUP BY u.email
        ORDER BY total_points DESC
            """, 
            nativeQuery = true)
    List<Object[]> getLeaderBoardRaw();
} 
