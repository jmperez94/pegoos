package com.asesoftware.peggos.persistence.contract;

public class Contract {

    public static enum tipoDato {
        INTEGER,
        REAL,
        TEXT,
        BLOB
    }

    public static final String IDENTITY = "AUTOINCREMENT";

    public static final String PRIMARY = "PRIMARY KEY";

    public static enum nulleable {
        NULL("NULL"), NOTNULL("NOT NULL");
        private final String texto;

        private nulleable(String texto) {
            this.texto = texto;
        }

        public String toString() {
            return this.texto;
        }
    }


}
