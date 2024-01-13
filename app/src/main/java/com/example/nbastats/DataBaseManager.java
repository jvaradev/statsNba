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

    //Metodos para Selects
    public Cursor getGamesByField(String field, String value) {
        String[] projection = {"game_id", "game_date", "home_points", "away_points", "home_id", "away_id", "mvp"};
        String selection = field + "=?";
        String[] selectionArgs = {value};
        String sortOrder = "game_id ASC";

        return database.query("game", projection, selection, selectionArgs, null, null, sortOrder);
    }

    public Cursor getPlayersByField(String field, String value) {
        String[] projection = {"player_id", "player_name", "player_apel", "player_position", "player_country", "team_id"};
        String selection = field + "=?";
        String[] selectionArgs = {value};
        String sortOrder = "player_id ASC";

        return database.query("player", projection, selection, selectionArgs, null, null, sortOrder);
    }

    public Cursor getTeamsByField(String field, String value) {
        String[] projection = {"team_id", "team_name", "team_city", "team_arena", "team_conference"};
        String selection = field + "=?";
        String[] selectionArgs = {value};
        String sortOrder = "team_id ASC";

        return database.query("team", projection, selection, selectionArgs, null, null, sortOrder);
    }

    public Cursor getStatsByField(String field, String value) {
        String[] projection = {"stat_id", "player_id", "game_id", "pointlastg", "pointperg",
                "reboundlasg", "reboundperg", "assitslastg", "assitsperg", "steallastg", "blocklastg", "lostlastg"};
        String selection = field + "=?";
        String[] selectionArgs = {value};
        String sortOrder = "stat_id ASC";

        return database.query("stat", projection, selection, selectionArgs, null, null, sortOrder);
    }
    public int getPlayerIdByName(String playerName) {
        Cursor cursor = database.rawQuery("SELECT player_id FROM player WHERE player_name=?", new String[]{playerName});
        int playerId = -1;
        if (cursor.moveToFirst()) {
            playerId = cursor.getInt(cursor.getColumnIndex("player_id"));
        }
        cursor.close();
        return playerId;
    }

    public Cursor getStatsByPlayerAndGame(int playerId, int gameId) {
        String[] projection = {"stat_id", "player_id", "game_id", "pointlastg", "pointperg",
                "reboundlasg", "reboundperg", "assitslastg", "assitsperg", "steallastg", "blocklastg", "lostlastg"};
        String selection = "player_id=? AND game_id=?";
        String[] selectionArgs = {String.valueOf(playerId), String.valueOf(gameId)};
        String sortOrder = "stat_id ASC";

        return database.query("stat", projection, selection, selectionArgs, null, null, sortOrder);
    }

    // Métodos Insert
    public long insertPlayer(String playerName, String playerApel, String playerPosition, String playerCountry, String teamId) {
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
    public long insertTeam(String teamName, String teamCity, String teamArena, String teamConference) {
        ContentValues values = new ContentValues();
        values.put("team_name", teamName);
        values.put("team_city", teamCity);
        values.put("team_arena", teamArena);
        values.put("team_conference", teamConference);

        return database.insert("team", null, values);
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

    //Metodos Deletes
    public int deleteGameById(int gameId) {
        return database.delete("game", "game_id=?", new String[]{String.valueOf(gameId)});
    }
    public int deletePlayerById(int playerId) {
        return database.delete("player", "player_id=?", new String[]{String.valueOf(playerId)});
    }
    public int deleteTeamByName(String teamName) {
        return database.delete("team", "team_name=?", new String[]{teamName});
    }
    public int deleteStatById(int statId) {
        return database.delete("stat", "stat_id=?", new String[]{String.valueOf(statId)});
    }

    //Metodos Updates
    public int updateGame(int gameId, String gameDate, int homePoints, int awayPoints, String homeId, String awayId, String mvp) {
        ContentValues values = new ContentValues();
        values.put("game_date", gameDate);
        values.put("home_points", homePoints);
        values.put("away_points", awayPoints);
        values.put("home_id", homeId);
        values.put("away_id", awayId);
        values.put("mvp", mvp);

        String whereClause = "game_id=?";
        String[] whereArgs = {String.valueOf(gameId)};

        return database.update("game", values, whereClause, whereArgs);
    }
    public int updatePlayer(int playerId, String playerName, String playerApel, String playerPosition, String playerCountry, String idTeam) {
        ContentValues values = new ContentValues();
        values.put("player_name", playerName);
        values.put("player_apel", playerApel);
        values.put("player_position", playerPosition);
        values.put("player_country", playerCountry);
        values.put("team_id", idTeam);

        String whereClause = "player_id=?";
        String[] whereArgs = {String.valueOf(playerId)};

        return database.update("player", values, whereClause, whereArgs);
    }

    public int updateTeam(int teamId, ContentValues values) {
        String whereClause = "team_id=?";
        String[] whereArgs = {String.valueOf(teamId)};
        return database.update("team", values, whereClause, whereArgs);
    }

    public int updateStat(int statId, int playerId, int gameId, int pointLastGame, double pointPerGame,
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

        String whereClause = "stat_id=?";
        String[] whereArgs = {String.valueOf(statId)};

        return database.update("stat", values, whereClause, whereArgs);
    }

}
