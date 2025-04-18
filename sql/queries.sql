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
