package com.unpri.charactermanager.model;              // baris 1: deklarasi package, HARUS baris pertama (kecuali komentar)

/**
 * Enum yang mendefinisikan jenis-jenis karakter dalam aplikasi.
 * Menggunakan enum (bukan String) agar nilai selalu valid & type-safe.
 */
public enum CharacterType {                             // baris 7: deklarasi enum, nama harus sama dengan nama file
    WARRIOR,    // karakter pejuang — HP & attack tinggi
    MAGE,       // karakter penyihir — attack tinggi, HP rendah
    ARCHER,     // karakter pemanah — seimbang, attack jarak jauh
    HEALER      // karakter penyembuh — HP tinggi, attack rendah
    
}