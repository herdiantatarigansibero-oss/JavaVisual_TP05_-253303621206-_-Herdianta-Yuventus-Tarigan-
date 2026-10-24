package com.unpri.charactermanager.model;

/**
 * Merepresentasikan satu karakter dalam aplikasi Character Manager.
 * Menerapkan prinsip Encapsulation: semua atribut private,
 * akses hanya melalui getter/setter.
 */
public class Character {

    // ===== ATRIBUT STATIC: milik CLASS, dipakai bersama oleh semua object =====
    private static int totalCharacterCreated = 0;

    // ===== ATRIBUT INSTANCE: milik masing-masing object =====
    private int id;
    private final String name;
    private final CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;   // default 0

    // ===== CONSTRUCTOR 1: membuat karakter BARU =====
    public Character(String name, CharacterType type, int level) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        totalCharacterCreated++;
    }

    // ===== CONSTRUCTOR 2: MEMUAT karakter dari database =====
    public Character(int id, String name, CharacterType type,
                     int level, int hitPoints, int attackPower) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        totalCharacterCreated++;
    }

    // ===== COPY CONSTRUCTOR: membuat object BARU yang independen =====
    // this(...) memanggil constructor 2, jadi counter hanya naik satu kali.
    public Character(Character other) {
        this(other.id, other.name, other.type, other.level,
             other.hitPoints, other.attackPower);
        this.mana = other.mana;
    }

    // ===== STATIC GETTER =====
    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    // ===== GETTER =====
    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }
    public int getMana() { return mana; }

    // ===== SETTER (dengan validasi) =====
    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level minimal adalah 1");
        }
        this.level = level;
        this.hitPoints = calculateBaseHP();       // statistik dihitung ulang
        this.attackPower = calculateBaseAttack();
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif");
        }
        this.mana = mana;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    // ===== equals(): membandingkan ISI (nama), bukan alamat memori =====
    /** Dua karakter dianggap sama jika namanya sama (case-sensitive). */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                   // object yang sama persis
        if (!(obj instanceof Character)) return false;  // bukan Character -> tidak sama
        Character other = (Character) obj;              // casting aman setelah instanceof
        return this.name.equals(other.name);
    }

    // hashCode() wajib konsisten dengan equals()
    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }

    // toString(): supaya println(character) tampil rapi
    @Override
    public String toString() {
        return name + " [" + type + ", Lv." + level
                + ", HP=" + hitPoints + ", ATK=" + attackPower + "]";
    }

    // ===== HELPER PRIVATE =====
    private int calculateBaseHP() {
        return 100 + (level * 10);   // level 5 -> HP 150
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);     // level 5 -> ATK 35
    }

}