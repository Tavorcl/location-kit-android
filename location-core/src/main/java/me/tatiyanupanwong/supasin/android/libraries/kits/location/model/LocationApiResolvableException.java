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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import android.app.PendingIntent;

import androidx.annotation.NonNull;

/**
 * A data class representing a resolvable exception from the location settings client.
 *
 * @since 1.1.0
 */
public interface LocationApiResolvableException extends LocationApiException {

    /**
     * The status code of the exception. Should be a RESOLUTION_REQUIRED (6) code from {@link LocationSettingsStatusCodes}.
     *
     * @since 1.1.0
     */
    int getStatusCode();

    /**
     * The pending intent of the exception. This should be used later so the user can resolve the exception.
     * Please note that in Lost the resultCode from this pending intent will never be {@code Activity#RESULT_OK}.
     *
     * @since 1.1.0
     */
    @NonNull PendingIntent getResolution();

}
