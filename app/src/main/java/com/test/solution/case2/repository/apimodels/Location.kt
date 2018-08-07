package com.test.solution.case2.repository.apimodels

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.solution.case2.utils.Config;

/**
 * Model which maps to a json object in the response.
 *
 * created by fahad on 07/08/2018
 */
data class Location(@SerializedName(Config.API_KEY_ID) val id : Int, @SerializedName(Config.API_KEY_NAME) val name : String, @SerializedName(Config.API_KEY_FROM_CENTRAL) val timeFromCentral: Transport, @SerializedName(Config.API_KEY_LOCATION) val latLng: LatLng) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readParcelable(Transport::class.java.classLoader),
            parcel.readParcelable(LatLng::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeParcelable(timeFromCentral, flags)
        parcel.writeParcelable(latLng, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}