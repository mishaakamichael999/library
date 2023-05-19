package project.library;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.sql.Blob;
import java.sql.Date;

public class Books {
    private final Integer id;
    private final String name;
    private final String author;
    private final Date publishDate;
    private final String quality;
    private final Date regDate;
    private final String status;
    private final Blob image;

    public Books(Integer id, String name, String author, Date publishDate, String quality, Date regDate, String status, Blob image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.quality = quality;
        this.regDate = regDate;
        this.status = status;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getQuality() {
        return quality;
    }

    public Date getRegDate() {
        return regDate;
    }

    public String getStatus() {
        return status;
    }

    public Blob getImage() {
        return image;
    }
}
