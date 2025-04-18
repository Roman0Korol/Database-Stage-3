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
-- This query retrieves the total number of consoles for each manufacturer 
-- and sorts the results in descending order based on the total number of consoles.
SELECT manufacturer, COUNT(name) AS total_consoles
FROM gaming_system
GROUP BY manufacturer
ORDER BY total_consoles DESC;



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
-- Find all developers that have Director in their role 
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

-- Query 9
-- Gets the best performing developers based on their game rating which is calculated based on average completion and playtime

WITH GameRatings AS (
    SELECT 
        Game.game_id,
        game_name,
        ROUND(AVG(completion_percentage),2) AS avg_completion,
        ROUND(AVG(playtime_hours),2) AS avg_playtime,
    	ROUND(AVG((playtime_hours+completion_percentage)/2),2) AS game_rating
    FROM 
        Profile
    JOIN 
        Game ON Profile.game_id = Game.game_id
    GROUP BY 
        Game.game_id, game_name
)

SELECT 
    Developer.developer_name,
    Studio.studio_name,
    GameRatings.game_name,
    GameRatings.avg_completion,
    GameRatings.avg_playtime,
        GameRatings.game_rating
FROM 
    Developer
JOIN 
    Developer_Studio ON Developer.developer_id = Developer_Studio.Developer_ID
JOIN 
    Studio ON Developer_Studio.studio_id = Studio.studio_id
JOIN 
    Game ON Studio.studio_id = Game.studio_id
JOIN 
    GameRatings ON Game.game_id = GameRatings.game_id
ORDER BY 
    GameRatings.game_rating DESC

