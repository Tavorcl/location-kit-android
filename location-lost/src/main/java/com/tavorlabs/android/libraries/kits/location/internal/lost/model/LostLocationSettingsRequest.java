/*
 * Copyright 2021 Tavorlabs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tavorlabs.android.libraries.kits.location.internal.lost.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;

public final class LostLocationSettingsRequest implements LocationSettingsRequest {

    private final com.mapzen.android.lost.api.LocationSettingsRequest mDelegate;

    public LostLocationSettingsRequest() {
        this.mDelegate = new com.mapzen.android.lost.api.LocationSettingsRequest.Builder()
                .addLocationRequest(com.mapzen.android.lost.api.LocationRequest.create())
                .build();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LostLocationSettingsRequest that = (LostLocationSettingsRequest) obj;

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


    public static @NonNull com.mapzen.android.lost.api.LocationSettingsRequest unwrap(
            @NonNull LocationSettingsRequest wrapped) {
        return ((LostLocationSettingsRequest) wrapped).mDelegate;
    }

    public static @NonNull LocationSettingsRequest wrap(
            @NonNull com.mapzen.android.lost.api.LocationSettingsRequest delegate) {
        return new LostLocationSettingsRequest();
    }

}
