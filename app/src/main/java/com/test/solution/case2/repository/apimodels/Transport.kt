package com.test.solution.case2.repository.apimodels

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.solution.case2.utils.Config;

//POJO
data class Transport(@SerializedName(Config.API_KEY_CAR) val timeByCar : String = "", @SerializedName(Config.API_KEY_TRAIN) val timeByTrain : String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(timeByCar)
        parcel.writeString(timeByTrain)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Transport> {
        override fun createFromParcel(parcel: Parcel): Transport {
            return Transport(parcel)
        }

        override fun newArray(size: Int): Array<Transport?> {
            return arrayOfNulls(size)
        }
    }
}