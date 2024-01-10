package com.example.nbastats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

    private DataBase dbHelper;
    private SQLiteDatabase database;

    public DataBaseManager(Context context) {
        dbHelper = new DataBase(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getGamesCursor() {
        // Definir la proyección con todas las columnas de la tabla "game"
        String[] projection = {"game_id", "game_date", "home_points", "away_points", "home_id", "away_id", "mvp"};

        // Puedes ajustar el orden según tus necesidades
        String sortOrder = "game_date DESC";

        // Realizar la consulta
        return database.query("game", projection, null, null, null, null, sortOrder);
    }


    // Métodos específicos para cada tabla

    // Métodos para la tabla player
    public long insertPlayer(String playerName, String playerApel, String playerPosition, String playerCountry, int teamId) {
        ContentValues values = new ContentValues();
        values.put("player_name", playerName);
        values.put("player_apel", playerApel);
        values.put("player_position", playerPosition);
        values.put("player_country", playerCountry);
        values.put("team_id", teamId);

        return database.insert("player", null, values);
    }

    public long insertGame(String gameDate, int homePoints, int awayPoints, String homeId, String awayId, String mvp) {
        ContentValues values = new ContentValues();
        values.put("game_date", gameDate);
        values.put("home_points", homePoints);
        values.put("away_points", awayPoints);
        values.put("home_id", homeId);
        values.put("away_id", awayId);
        values.put("mvp", mvp);

        return database.insert("game", null, values);
    }

    public long insertStat(int playerId, int gameId, int pointLastGame, double pointPerGame,
                           int reboundLastGame, double reboundPerGame, int assistsLastGame,
                           double assistsPerGame, int stealsLastGame, int blocksLastGame, int lostLastGame) {
        ContentValues values = new ContentValues();
        values.put("player_id", playerId);
        values.put("game_id", gameId);
        values.put("pointlastg", pointLastGame);
        values.put("pointperg", pointPerGame);
        values.put("reboundlasg", reboundLastGame);
        values.put("reboundperg", reboundPerGame);
        values.put("assitslastg", assistsLastGame);
        values.put("assitsperg", assistsPerGame);
        values.put("steallastg", stealsLastGame);
        values.put("blocklastg", blocksLastGame);
        values.put("lostlastg", lostLastGame);

        return database.insert("stat", null, values);
    }

    public long insertTeam(String teamName, String teamCity, String teamArena, String teamConference,
                           int homeId, int awayId) {
        ContentValues values = new ContentValues();
        values.put("team_name", teamName);
        values.put("team_city", teamCity);
        values.put("team_arena", teamArena);
        values.put("team_conference", teamConference);
        values.put("home_id", homeId);
        values.put("away_id", awayId);

        return database.insert("team", null, values);
    }

    public Cursor getPlayersByTeam(int teamId) {
        return database.rawQuery("SELECT * FROM player WHERE team_id=?", new String[]{String.valueOf(teamId)});
    }

    public Cursor getGamesByDate(String gameDate) {
        return database.rawQuery("SELECT * FROM game WHERE game_date=?", new String[]{gameDate});
    }

    public Cursor getStatsByPlayer(int playerId) {
        return database.rawQuery("SELECT * FROM stat WHERE player_id=?", new String[]{String.valueOf(playerId)});
    }

    public Cursor getTeamsByConference(String conference) {
        return database.rawQuery("SELECT * FROM team WHERE team_conference=?", new String[]{conference});
    }
}
