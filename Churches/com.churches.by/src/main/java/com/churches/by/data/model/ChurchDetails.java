package com.churches.by.data.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChurchDetails implements Parcelable {

    private final Church church;
    private final Bitmap image;
    private final List<ChurchEvent> events;
    private final List<ScheduleItem> scheduleItems;
    private final String description;

    public ChurchDetails(Church church, Bitmap image, List<ChurchEvent> events, List<ScheduleItem> scheduleItems, String description) {
        this.church = church;
        this.image = image;
        this.events = events;
        this.scheduleItems = scheduleItems;
        this.description = description;
    }

    private ChurchDetails(Builder builder) {
        church = builder.church;
        image = builder.image;
        events = builder.events;
        scheduleItems = builder.scheduleItems;
        description = builder.description;
    }

    public ChurchDetails(Parcel in) {
        church = in.readParcelable(Church.class.getClassLoader());
        image = in.readParcelable(Bitmap.class.getClassLoader());

        events = new ArrayList<>();
        in.readTypedList(events, ChurchEvent.CREATOR);

        scheduleItems = new ArrayList<>();
        in.readTypedList(scheduleItems, ScheduleItem.CREATOR);

        description = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(church, flags);
        dest.writeParcelable(image, flags);
        dest.writeTypedList(events);
        dest.writeTypedList(scheduleItems);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ChurchDetails> CREATOR = new Parcelable.Creator<ChurchDetails>() {
        @Override
        public ChurchDetails createFromParcel(Parcel in) {
            return new ChurchDetails(in);
        }

        @Override
        public ChurchDetails[] newArray(int size) {
            return new ChurchDetails[size];
        }
    };

    public List<ScheduleItem> scheduleItems() {
        return scheduleItems;
    }

    public static final class Builder {
        private Church church;
        private Bitmap image;
        private List<ChurchEvent> events;
        private List<ScheduleItem> scheduleItems;
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
