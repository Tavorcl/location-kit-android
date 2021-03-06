package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationResult;

public final class HuaweiLocationResult implements LocationResult {

    private final com.huawei.hms.location.LocationResult mDelegate;

    private HuaweiLocationResult(
            @NonNull com.huawei.hms.location.LocationResult delegate) {
        mDelegate = delegate;
    }

    @Override
    public @Nullable Location getLastLocation() {
        return mDelegate.getLastLocation();
    }

    @Override
    public @NonNull List<Location> getLocations() {
        return mDelegate.getLocations();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HuaweiLocationResult that = (HuaweiLocationResult) obj;

        return mDelegate.equals(that.mDelegate);
    }

    @Override
    public int hashCode() {
        return mDelegate.hashCode();
    }

    @Override
    public @NonNull String toString() {
        return mDelegate.toString();
    }


    public static @NonNull LocationResult wrap(
            @NonNull com.huawei.hms.location.LocationResult delegate) {
        return new HuaweiLocationResult(delegate);
    }

}
