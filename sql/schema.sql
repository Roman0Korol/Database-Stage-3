-- Minimal Player table
CREATE TABLE Player (
    ID INT,
    Username VARCHAR(50),
    Email VARCHAR(100),
    Date_Joined DATE,
    Region VARCHAR(10)
);

-- Minimal Developer table
CREATE TABLE Developer (
    developer_id INT,
    developer_name VARCHAR(100)
);

-- Minimal Developer_Project table
CREATE TABLE Developer_Project (
    Developer_ID INT,
    Project VARCHAR(100)
);

-- Minimal Project_Role table
CREATE TABLE Project_Role (
    Project VARCHAR(100),
    Role VARCHAR(100)
);

-- Minimal Studio table
CREATE TABLE Studio (
    studio_id INT,
    studio_name VARCHAR(100),
    studio_location VARCHAR(100),
    studio_website VARCHAR(100)
);

-- Minimal Game table
CREATE TABLE Game (
    game_id INT,
    game_name VARCHAR(100),
    release_date DATE,
    genre VARCHAR(50),
    studio_id INT
);

-- Minimal Gaming_System table
CREATE TABLE Gaming_System (
    System_id INT,
    name VARCHAR(50),
    manufacturer VARCHAR(50),
    Release_year INT
);

-- Minimal Profile table
CREATE TABLE Profile (
    profile_id INT,
    player_id INT,
    game_id INT,
    creation_date DATE,
    playtime_hours INT,
    purchase_date DATE,
    completion_percentage FLOAT
);

-- Minimal Developer_Studio table
CREATE TABLE Developer_Studio (
    Developer_ID INT,
    studio_id INT
);

-- Minimal Game_Compatibility table
CREATE TABLE Game_Compatibility (
    system_id INT,
    game_id INT
);

-- Minimal Stats table
CREATE TABLE Stats (
    stats_id INT,
    profile_id INT,
    game_id INT
);
