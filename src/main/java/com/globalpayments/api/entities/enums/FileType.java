package com.globalpayments.api.entities.enums;

public enum FileType {
    TIF("TIF"),
    TIFF("TIFF"),
    PDF("PDF"),
    BMP("BMP"),
    JPEG("JPEG"),
    GIF("GIF"),
    PNG("PNG"),
    DOC("DOC"),
    DOCX("DOCX");

    private final String value;

    FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FileType fromValue(String value) {
        for (FileType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
