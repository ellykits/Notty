package com.nerdsoft.coder.notty.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

@Entity
public class Note {

    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Property(nameInDb = "title")
    private String title;
    @NotNull
    @Property(nameInDb = "details")
    private String details;

    @Property(nameInDb = "created_at")
    private Date createdAt;

    @Property(nameInDb = "updated_at")
    private Date updatedAt;

    @Generated(hash = 1969230037)
    public Note(Long id, @NotNull String title, @NotNull String details,
            Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}
