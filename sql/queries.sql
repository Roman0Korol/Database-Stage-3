-- Query  1 
-- Get players with less than 50 hours of playtime
SELECT  
    player.ID,            
    player.Username,      
    profile.playtime_hours 
FROM 
    player
JOIN 
    profile ON player.ID = profile.player_id
WHERE 
    profile.playtime_hours < 50;


-- Query 2 
-- Show players whose playtime is greater than the average playtime
SELECT player.Username, SUM(profile.playtime_hours) AS total_playtime
FROM player
JOIN profile ON player.ID = profile.player_id
GROUP BY player.Username
HAVING total_playtime > (
  SELECT AVG(playtime_hours) FROM profile
);
