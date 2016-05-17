DROP TABLE IF EXISTS background_objects;
DROP TABLE IF EXISTS levels;
DROP TABLE IF EXISTS level_background_objects;
DROP TABLE IF EXISTS level_asteroids;
DROP TABLE IF EXISTS asteroid_types;
DROP TABLE IF EXISTS main_bodies;
DROP TABLE IF EXISTS cannons;
DROP TABLE IF EXISTS extra_parts;
DROP TABLE IF EXISTS engines;
DROP TABLE IF EXISTS power_cores;

CREATE TABLE background_objects
(
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	path_to TEXT NOT NULL,
);

CREATE TABLE levels
(
	id INTEGER NOT NULL PRIMARY KEY,
	title TEXT NOT NULL,
	hint TEXT NOT NULL,
	width INTEGER NOT NULL,
	height INTEGER NOT NULL,
	music TEXT NOT NULL,
);

CREATE TABLE asteroid_types
(
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	path_to TEXT NOT NULL,
	width INTEGER NOT NULL,
	height INTEGER NOT NULL,
);

-- mapping tables

CREATE TABLE level_background_objects
(
	level_id INTEGER NOT NULL FOREIGN KEY REFERENCES levels(id),
	object_id INTEGER NOT NULL FOREIGN KEY REFERENCES background_objects(id),
	path_to TEXT NOT NULL,
	scale REAL NOT NULL,
);

CREATE TABLE level_asteroid_types
(
	level_id INTEGER NOT NULL FOREIGN KEY REFERENCES levels(id),
	asteroid_id INTEGER NOT NULL FOREIGN KEY REFERENCES asteroid_types(id),
	count INTEGER NOT NULL,
);

-- ship part tables

CREATE TABLE main_bodies
(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	attach_point TEXT NOT NULL,
	path_to TEXT NOT NULL,
	width INT NOT NULL,
	height INT NOT NULL,
);

CREATE TABLE cannons
(	
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	path_to TEXT NOT NULL,
	attach_point TEXT NOT NULL,
	emit_point TEXT NOT NULL,
	width INTEGER NOT NULL,
	height INTEGER NOT NULL,
	path_to_attack TEXT NOT NULL,
	width_attack INTEGER NOT NULL,
	height_attack INTEGER NOT NULL,
	sound_attack TEXT NOT NULL,
	damage INTEGER NOT NULL,
);

CREATE TABLE extra_parts
(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	path_to TEXT NOT NULL,
	width INTEGER NOT NULL,
	height INTEGER NOT NULL,
);

CREATE TABLE engines
(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	base_speed INTEGER NOT NULL,
	base_turn_rate INTEGER NOT NULL,
	attach_point TEXT NOT NULL,
	path_to TEXT NOT NULL,
	width INTEGER NOT NULL,
	height INTEGER NOT NULL,
);

CREATE TABLE power_cores
(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	cannon_boost INTEGER NOT NULL,
	engine_boost INTEGER NOT NULL,
	path_to TEXT NOT NULL,
);