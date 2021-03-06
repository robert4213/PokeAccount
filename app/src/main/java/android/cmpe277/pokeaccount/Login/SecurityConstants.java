/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.cmpe277.pokeaccount.Login;

public class SecurityConstants {
    public static final String KEYSTORE_PROVIDER_ANDROID_KEYSTORE = "AndroidKeyStore";

    public static final String TYPE_RSA = "RSA";
    public static final String TYPE_DSA = "DSA";
    public static final String TYPE_BKS = "BKS";

    public static final String SIGNATURE_SHA256withRSA = "SHA256withRSA";
    public static final String SIGNATURE_SHA512withRSA = "SHA512withRSA";

    public static final String SHARED_PERFERENCE = "POKEACCOUNT";
    public static final String SIGNATURE = "POKEACCOUNT_SIGNATURE";

    protected static final String ALIAS = "myKey";
}
