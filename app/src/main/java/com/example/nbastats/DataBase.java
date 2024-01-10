package com.example.nbastats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "nbastats.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PLAYER_CREATE = "CREATE TABLE IF NOT EXISTS player (\n" +
            "    player_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    player_name TEXT,\n" +
            "    player_apel TEXT,\n" +
            "    player_position TEXT,\n" +
            "    player_country TEXT,\n" +
            "    team_id INTEGER\n" +
            ");";

    private static final String TABLE_TEAM_CREATE = "CREATE TABLE IF NOT EXISTS team (\n" +
            "    team_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    team_name TEXT,\n" +
            "    team_city TEXT,\n" +
            "    team_arena TEXT,\n" +
            "    team_conference TEXT,\n" +
            "    home_id INTEGER,\n" +
            "    away_id INTEGER\n" +
            ");";

    private static final String TABLE_STATS_CREATE = "CREATE TABLE IF NOT EXISTS stat (\n" +
            "    stat_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    player_id INTEGER,\n" +
            "    game_id INTEGER,\n" +
            "    pointlastg INTEGER,\n" +
            "    pointperg DOUBLE,\n" +
            "    reboundlasg INTEGER,\n" +
            "    reboundperg DOUBLE,\n" +
            "    assitslastg INTEGER,\n" +
            "    assitsperg DOUBLE,\n" +
            "    steallastg INTEGER,\n" +
            "    blocklastg INTEGER,\n" +
            "    lostlastg INTEGER\n" +
            ");";

    private static final String TABLE_GAME_CREATE = "CREATE TABLE IF NOT EXISTS game (game_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "game_date TEXT,\n" +
            "home_points INTEGER,\n" +
            "away_points INTEGER,\n" +
            "home_id TEXT,\n" +
            "away_id TEXT,\n" +
            "mvp TEXT);";



    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_TEAM_CREATE);
        db.execSQL(TABLE_PLAYER_CREATE);
        db.execSQL(TABLE_GAME_CREATE);
        db.execSQL(TABLE_STATS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS player");
        db.execSQL("DROP TABLE IF EXISTS team");
        db.execSQL("DROP TABLE IF EXISTS game");
        db.execSQL("DROP TABLE IF EXISTS stat");
        onCreate(db);
    }
}
