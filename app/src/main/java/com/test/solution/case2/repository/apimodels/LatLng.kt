package com.test.solution.case2.repository.apimodels

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.solution.case2.utils.Config;

//POJO
data class LatLng(@SerializedName(Config.API_KEY_LATITUDE) val latitude : Double, @SerializedName(Config.API_KEY_LONGITUDE) val longitude : Double) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readDouble(),
            parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LatLng> {
        override fun createFromParcel(parcel: Parcel): LatLng {
            return LatLng(parcel)
        }

        override fun newArray(size: Int): Array<LatLng?> {
            return arrayOfNulls(size)
        }
    }
}