-- Populate Player table
INSERT INTO Player (ID, Username, Email, Date_Joined, Region) VALUES
(1, 'Shadow Ninja', 'sninja@example.com', '2023-01-15', 'NA'),
(2, 'MageFire', 'magefire@game.com', '2023-02-20', 'EU'),
(3, 'ArcherQueen', 'aqueen@archers.net', '2023-03-10', 'AS'),
(4, 'TankLord', 'tlord@tanks.org', '2023-04-05', 'NA'),
(5, 'Stealthy', 'stealth@rogues.com', '2023-05-12', 'EU'),
(6, 'DragonSlayer', 'dragslay@legends.co', '2023-07-22', 'AS'),
(7, 'PixelHunter', 'phunter@example.com', '2023-08-14', 'NA'),
(8, 'NecroQueen', 'nqueen@dark.net', '2023-09-05', 'EU'),
(9, 'SpeedRunner99', 'srunner@fast.com', '2023-10-22', 'AS'),
(10, 'LootGoblin', 'lgoblin@treasure.org', '2023-11-30', 'NA'),
(11, 'MagicMike', 'mmike@wizards.co', '2023-12-15', 'EU'),
(12, 'SniperWolf', 'swolf@shooters.net', '2024-01-10', 'AS'),
(13, 'EpicGamer', 'epic@gamer.com', '2024-02-15', 'NA'),
(14, 'NoobMaster', 'noob@master.com', '2024-03-20', 'EU'),
(15, 'ProGamer123', 'pro@gamer.com', '2024-04-25', 'AS');

-- Populate Developer table
INSERT INTO Developer (developer_id, developer_name) VALUES
(1, 'Hideo Kojima'),
(2, 'John Carmack'),
(3, 'Hideki Kamiya'),
(4, 'Yoko Taro'),
(5, 'Todd Howard'),
(6, 'Shigeru Miyamoto'),
(7, 'Gabe Newell'),
(8, 'Amy Hennig'),
(9, 'Neil Druckmann'),
(10, 'Tim Schafer'),
(11, 'Cliff Bleszinski'),
(12, 'Hironobu Sakaguchi'),
(13, 'Ken Levine'),
(14, 'Jade Raymond'),
(15, 'Warren Spector');

-- Populate Studio table
INSERT INTO Studio (studio_id, studio_name, studio_location, studio_website) VALUES
(1, 'Naughty Dog', 'Santa Monica, CA', 'naughtydog.com'),
(2, 'Nintendo EPD', 'Kyoto, Japan', 'nintendo.com'),
(3, 'CD Projekt Red', 'Warsaw, Poland', 'cdprojektred.com'),
(4, 'Bethesda Game Studios', 'Rockville, MD', 'bethesda.net'),
(5, 'Nintendo EAD', 'Kyoto, Japan', 'nintendo.com'),
(6, 'Valve', 'Bellevue, WA', 'valvesoftware.com'),
(7, 'Double Fine', 'San Francisco, CA', 'doublefine.com'),
(8, 'PlatinumGames', 'Osaka, Japan', 'platinumgames.com'),
(9, 'Square Enix', 'Tokyo, Japan', 'square-enix.com'),
(10, 'Ubisoft Montreal', 'Montreal, Canada', 'ubisoft.com'),
(11, 'Rockstar North', 'Edinburgh, UK', 'rockstargames.com'),
(12, 'FromSoftware', 'Tokyo, Japan', 'fromsoftware.jp'),
(13, 'Santa Monica Studio', 'Los Angeles, CA', 'sms.playstation.com'),
(14, 'Insomniac Games', 'Burbank, CA', 'insomniac.games'),
(15, 'BioWare', 'Edmonton, Canada', 'bioware.com');

-- Populate Developer_Project table
INSERT INTO Developer_Project (Developer_ID, Project) VALUES
(1, 'Death Stranding'),
(2, 'Doom'),
(3, 'Bayonetta'),
(4, 'Nier: Automata'),
(5, 'The Elder Scrolls V: Skyrim'),
(6, 'Super Mario Bros.'),
(7, 'Half-Life'),
(8, 'Uncharted'),
(9, 'The Last of Us'),
(10, 'Psychonauts'),
(11, 'Gears of War'),
(12, 'Final Fantasy'),
(13, 'BioShock'),
(14, 'Assassin''s Creed'),
(15, 'Deus Ex');

-- Populate Project_Role table
INSERT INTO Project_Role (Project, Role) VALUES
('Death Stranding', 'Director'),
('Doom', 'Lead Programmer'),
('Bayonetta', 'Game Director'),
('Nier: Automata', 'Creative Director'),
('The Elder Scrolls V: Skyrim', 'Game Director'),
('Super Mario Bros.', 'Designer'),
('Half-Life', 'Programmer'),
('Uncharted', 'Writer'),
('The Last of Us', 'Creative Director'),
('Psychonauts', 'Game Designer'),
('Gears of War', 'Design Director'),
('Final Fantasy', 'Producer'),
('BioShock', 'Creative Director'),
('Assassin''s Creed', 'Producer'),
('Deus Ex', 'Director');

-- Populate Game table
INSERT INTO Game (game_id, game_name, release_date, genre, studio_id) VALUES
(1, 'The Witcher 3: Wild Hunt', '2015-05-19', 'RPG', 3),
(2, 'Elden Ring', '2022-02-25', 'Action-RPG', 12),
(3, 'Cyberpunk 2077', '2020-12-10', 'Action-RPG', 3),
(4, 'God of War: Ragnarök', '2022-11-09', 'Action-Adventure', 13),
(5, 'The Legend of Zelda: Breath of the Wild', '2017-03-03', 'Adventure', 2),
(6, 'Starfield', '2023-09-06', 'Action-RPG', 4),
(7, 'Super Mario Odyssey', '2017-10-27', 'Platformer', 5),
(8, 'Half-Life: Alyx', '2020-03-23', 'VR Shooter', 6),
(9, 'Uncharted 4: A Thief''s End', '2016-05-10', 'Action-Adventure', 1),
(10, 'The Last of Us Part II', '2020-06-19', 'Action-Adventure', 1),
(11, 'Psychonauts 2', '2021-08-25', 'Platformer', 7),
(12, 'Nier: Automata', '2017-03-07', 'Action-RPG', 8),
(13, 'Red Dead Redemption 2', '2018-10-26', 'Action-Adventure', 11),
(14, 'Marvel''s Spider-Man 2', '2023-10-20', 'Action-Adventure', 14),
(15, 'Mass Effect Legendary Edition', '2021-05-14', 'RPG', 15);

-- Populate Gaming_System table
INSERT INTO Gaming_System (System_id, name, manufacturer, Release_year) VALUES
(1, 'PlayStation 5', 'Sony', 2020),
(2, 'Xbox Series X', 'Microsoft', 2020),
(3, 'Nintendo Switch', 'Nintendo', 2017),
(4, 'Xbox One', 'Microsoft', 2013),
(5, 'PlayStation 4', 'Sony', 2013),
(6, 'Nintendo Switch OLED', 'Nintendo', 2021),
(7, 'Steam Deck', 'Valve', 2022),
(8, 'PlayStation VR2', 'Sony', 2023),
(9, 'Xbox Series S', 'Microsoft', 2020),
(10, 'PlayStation VR', 'Sony', 2016),
(11, 'Nintendo 3DS', 'Nintendo', 2011),
(12, 'PlayStation Vita', 'Sony', 2011),
(13, 'Wii U', 'Nintendo', 2012),
(14, 'PlayStation 3', 'Sony', 2006),
(15, 'Xbox 360', 'Microsoft', 2005);

-- Populate Profile table
INSERT INTO Profile (profile_id, player_id, game_id, creation_date, playtime_hours, purchase_date, completion_percentage) VALUES
(1, 1, 3, '2023-01-20', 45, '2023-01-24', 78.5),
(2, 2, 1, '2023-01-30', 121, '2023-02-01', 100),
(3, 3, 4, '2023-01-10', 41, '2023-01-16', 62.5),
(4, 3, 5, '2023-01-25', 53, '2023-01-30', 21),
(5, 4, 6, '2023-09-10', 85, '2023-09-06', 45.2),
(6, 5, 7, '2023-10-15', 32, '2023-10-10', 100),
(7, 6, 8, '2023-11-20', 18, '2023-11-18', 12.5),
(8, 7, 9, '2023-12-05', 47, '2023-12-01', 78.3),
(9, 8, 10, '2024-01-12', 62, '2024-01-05', 92.1),
(10, 9, 11, '2024-02-18', 28, '2024-02-15', 35.7),
(11, 10, 12, '2024-03-22', 56, '2024-03-20', 68.9),
(12, 11, 1, '2024-04-05', 112, '2024-04-01', 100),
(13, 12, 2, '2024-04-10', 89, '2024-04-08', 87.4),
(14, 13, 13, '2024-04-15', 145, '2024-04-12', 95.2),
(15, 14, 14, '2024-04-20', 42, '2024-04-18', 65.8),
(16, 15, 15, '2024-04-25', 76, '2024-04-22', 53.1),
(17, 1, 2, '2023-05-15', 68, '2023-05-10', 89.3),
(18, 2, 3, '2023-06-20', 54, '2023-06-15', 72.6),
(19, 4, 4, '2023-07-25', 37, '2023-07-20', 58.9),
(20, 5, 6, '2023-08-30', 92, '2023-08-25', 41.7);

-- Populate Developer_Studio table
INSERT INTO Developer_Studio (Developer_ID, studio_id) VALUES
(1, 9),  -- Hideo Kojima at Kojima Productions (not in our list, using Square Enix)
(2, 6),  -- John Carmack at id Software (not in our list, using Valve)
(3, 8),  -- Hideki Kamiya at PlatinumGames
(4, 8),  -- Yoko Taro at PlatinumGames
(5, 4),  -- Todd Howard at Bethesda
(6, 2),  -- Shigeru Miyamoto at Nintendo
(7, 6),  -- Gabe Newell at Valve
(8, 1),  -- Amy Hennig at Naughty Dog
(9, 1),  -- Neil Druckmann at Naughty Dog
(10, 7), -- Tim Schafer at Double Fine
(11, 15),-- Cliff Bleszinski at Epic Games (not in our list, using BioWare)
(12, 9), -- Hironobu Sakaguchi at Square Enix
(13, 15),-- Ken Levine at Irrational Games (not in our list, using BioWare)
(14, 10),-- Jade Raymond at Ubisoft
(15, 15);-- Warren Spector at Ion Storm (not in our list, using BioWare)

-- Populate Game_Compatibility table
INSERT INTO Game_Compatibility (system_id, game_id) VALUES
-- PlayStation 5
(1, 1), (1, 2), (1, 3), (1, 4), (1, 6), (1, 9), (1, 10), (1, 12), (1, 13), (1, 14),
-- Xbox Series X
(2, 1), (2, 2), (2, 3), (2, 6), (2, 13),
-- Nintendo Switch
(3, 2), (3, 5), (3, 7), (3, 11),
-- Xbox One
(4, 1), (4, 3), (4, 9), (4, 13),
-- PlayStation 4
(5, 1), (5, 3), (5, 4), (5, 6), (5, 9), (5, 10), (5, 12), (5, 13),
-- Nintendo Switch OLED
(6, 2), (6, 5), (6, 7), (6, 11),
-- Steam Deck
(7, 1), (7, 2), (7, 3), (7, 6), (7, 8), (7, 11), (7, 13), (7, 15),
-- PlayStation VR2
(8, 8),
-- Xbox Series S
(9, 1), (9, 2), (9, 3), (9, 6), (9, 13),
-- PlayStation VR
(10, 8),
-- Nintendo 3DS
(11, 5),
-- PlayStation Vita
(12, 9),
-- Wii U
(13, 5),
-- PlayStation 3
(14, 1), (14, 9),
-- Xbox 360
(15, 1), (15, 13);

-- Populate Stats table
INSERT INTO Stats (stats_id, profile_id, game_id) VALUES
(1, 1, 3),
(2, 2, 1),
(3, 3, 4),
(4, 4, 5),
(5, 5, 6),
(6, 6, 7),
(7, 7, 8),
(8, 8, 9),
(9, 9, 10),
(10, 10, 11),
(11, 11, 12),
(12, 12, 1),
(13, 13, 2),
(14, 14, 13),
(15, 15, 14),
(16, 16, 15),
(17, 17, 2),
(18, 18, 3),
(19, 19, 4),
(20, 20, 6);
