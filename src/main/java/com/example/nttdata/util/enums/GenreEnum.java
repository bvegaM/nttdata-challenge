package com.example.nttdata.util.enums;

public enum GenreEnum {

    M("masculino"),
    F("femenino");

    private String genre;

    GenreEnum(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
