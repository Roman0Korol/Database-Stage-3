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


-- Query 6 
-- Show players whose playtime is greater than the average playtime
SELECT player.Username, SUM(profile.playtime_hours) AS total_playtime
FROM player
JOIN profile ON player.ID = profile.player_id
GROUP BY player.Username
HAVING total_playtime > (
  SELECT AVG(playtime_hours) FROM profile
);

-- Query 7
-- Classify players based on their total playtime hours
SELECT 
    Player.Username,
    SUM(Profile.playtime_hours) AS total_playtime,
    CASE
        WHEN SUM(Profile.playtime_hours) > 100 THEN 'Hardcore'
        WHEN SUM(Profile.playtime_hours) > 50 THEN 'Regular'
        WHEN SUM(Profile.playtime_hours) > 10 THEN 'Casual'
        ELSE 'Newbie'
    END AS player_category
FROM 
    Player
JOIN 
    Profile ON Player.ID = Profile.player_id
GROUP BY 
    Player.Username
ORDER BY 
    total_playtime DESC;


-- Query 8 
-- Find all developers with director roles
SELECT DISTINCT
    Developer.developer_name,
    Project_Role.Role
FROM 
    Developer
JOIN 
    Developer_Project ON Developer.developer_id = Developer_Project.Developer_ID
JOIN 
    Project_Role ON Developer_Project.Project = Project_Role.Project
WHERE 
    Project_Role.Role LIKE '%Director%'
ORDER BY 
    Developer.developer_name;


