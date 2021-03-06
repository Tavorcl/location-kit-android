package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;

import me.tatiyanupanwong.supasin.android.libraries.kits.internal.huawei.tasks.HuaweiTask;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.interceptors.LocationResultInterceptor;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.interceptors.VoidResultInterceptor;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.FusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationCallback;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationListener;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.Task;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
public final class HuaweiFusedLocationProviderClient implements FusedLocationProviderClient {

    private final com.huawei.hms.location.FusedLocationProviderClient mDelegate;

    private final HuaweiLocationCallbacksHolder mLocationCallbacksHolder =
            new HuaweiLocationCallbacksHolder();

    private final HuaweiLocationListenersHolder mLocationListenersHolder =
            new HuaweiLocationListenersHolder();

    public HuaweiFusedLocationProviderClient(@NonNull Context context) {
        mDelegate = com.huawei.hms.location.LocationServices
                .getFusedLocationProviderClient(context);
    }

    public HuaweiFusedLocationProviderClient(@NonNull Activity activity) {
        mDelegate = com.huawei.hms.location.LocationServices
                .getFusedLocationProviderClient(activity);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Location> getLastLocation() {
        return new HuaweiTask<>(
                mDelegate.getLastLocation(),
                LocationResultInterceptor.INSTANCE
        );
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationCallback callback) {
        return requestLocationUpdates(request, callback, null);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationListener listener) {
        return requestLocationUpdates(request, listener, null);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            final @NonNull LocationCallback callback, @Nullable Looper looper) {
        return new HuaweiTask<>(
                mDelegate.requestLocationUpdates(
                        HuaweiLocationRequest.unwrap(request),
                        mLocationCallbacksHolder.put(callback),
                        looper),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationListener listener, @Nullable Looper looper) {
        return new HuaweiTask<>(
                mDelegate.requestLocationUpdates(
                        HuaweiLocationRequest.unwrap(request),
                        mLocationListenersHolder.put(listener),
                        looper),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    public @NonNull Task<Void> removeLocationUpdates(@NonNull LocationCallback callback) {
        return new HuaweiTask<>(
                mDelegate.removeLocationUpdates(mLocationCallbacksHolder.remove(callback)),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    public @NonNull Task<Void> removeLocationUpdates(@NonNull LocationListener listener) {
        return new HuaweiTask<>(
                mDelegate.removeLocationUpdates(mLocationListenersHolder.remove(listener)),
                VoidResultInterceptor.INSTANCE
        );
    }

}
