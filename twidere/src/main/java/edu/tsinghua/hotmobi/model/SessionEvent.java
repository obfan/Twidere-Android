/*
 *                 Twidere - Twitter client for Android
 *
 *  Copyright (C) 2012-2015 Mariotaku Lee <mariotaku.lee@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.tsinghua.hotmobi.model;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;
import com.hannesdorfmann.parcelableplease.annotation.ParcelableThisPlease;

/**
 * Created by mariotaku on 15/8/8.
 */
@ParcelablePlease
@JsonObject
public class SessionEvent extends BaseEvent implements Parcelable {

    public static final Creator<SessionEvent> CREATOR = new Creator<SessionEvent>() {
        @Override
        public SessionEvent createFromParcel(Parcel in) {
            return new SessionEvent(in);
        }

        @Override
        public SessionEvent[] newArray(int size) {
            return new SessionEvent[size];
        }
    };

    @ParcelableThisPlease
    @JsonField(name = "configuration")
    String configuration;

    protected SessionEvent(Parcel in) {
        super(in);
        SessionEventParcelablePlease.readFromParcel(this, in);
    }

    public SessionEvent() {

    }

    public static SessionEvent create(Context context) {
        final SessionEvent event = new SessionEvent();
        event.markStart(context);
        final Context appContext = context.getApplicationContext();
        final Configuration conf = appContext.getResources().getConfiguration();
        event.setConfiguration(conf.toString());
        return event;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        SessionEventParcelablePlease.writeToParcel(this, dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
}
