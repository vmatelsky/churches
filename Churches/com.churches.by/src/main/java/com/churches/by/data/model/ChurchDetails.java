package com.churches.by.data.model;

import android.graphics.Bitmap;

import java.util.Collections;
import java.util.List;

public class ChurchDetails {

    private final Church church;
    private final Bitmap image;
    private final List<ChurchEvent> events;
    private final String description;

    public ChurchDetails(Church church, Bitmap image, List<ChurchEvent> events, String description) {
        this.church = church;
        this.image = image;
        this.events = events;
        this.description = description;
    }

    private ChurchDetails(Builder builder) {
        church = builder.church;
        image = builder.image;
        events = builder.events;
        description = builder.description;
    }

    public Church church() {
        return church;
    }

    public Bitmap image() {
        return image;
    }

    public List<ChurchEvent> events() {
        return Collections.unmodifiableList(events);
    }

    public String description() {
        return description;
    }

    public static final class Builder {
        private Church church;
        private Bitmap image;
        private List<ChurchEvent> events;
        private String description;

        public Builder() {
        }

        public Builder church(Church church) {
            this.church = church;
            return this;
        }

        public Builder image(Bitmap image) {
            this.image = image;
            return this;
        }

        public Builder events(List<ChurchEvent> events) {
            this.events = events;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public ChurchDetails build() {
            return new ChurchDetails(this);
        }
    }
}
