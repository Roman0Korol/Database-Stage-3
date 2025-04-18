-- Query  1 
-- Select the player ID, username, and playtime hours
SELECT  
    player.ID,                 -- Player's unique ID from the 'player' table
    player.Username,           -- Player's username from the 'player' table
    profile.playtime_hours     -- Corresponding playtime hours from the 'profile' table
FROM 
    player
-- Join the 'profile' table where the player ID matches in both tables
JOIN 
    profile 
    ON player.ID = profile.player_id
-- Filter the results to show only players with less than 50 hours of playtime
WHERE 
    profile.playtime_hours < '50';
