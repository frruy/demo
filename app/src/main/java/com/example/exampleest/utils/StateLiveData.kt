package com.example.exampleest.utils

import androidx.annotation.Nullable
import androidx.lifecycle.MutableLiveData


class StateLiveData<T> : MutableLiveData<StateLiveData.StateData<T>?>() {
    /**
     * Use this to put the Data on a LOADING Status
     */
    fun postLoading() {
        postValue(StateData<T>().loading())
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    fun postError(throwable: Throwable?) {
        postValue(throwable?.let { StateData<T>().error(it) })
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    fun postSuccess(data: T) {
        postValue(StateData<T>().success(data))
    }

    /**
     * Use this to put the Data on a COMPLETE DataStatus
     */
    fun postComplete() {
        postValue(StateData<T>().complete())
    }

    class StateData<T> {
        var status: DataStatus
            private set

        @get:Nullable
        @Nullable
        var data: T?
            private set

        @get:Nullable
        @Nullable
        var error: Throwable?
            private set

        fun loading(): StateData<T> {
            status = DataStatus.LOADING
            data = null
            error = null
            return this
        }

        fun success(data: T): StateData<T> {
            status = DataStatus.SUCCESS
            this.data = data
            error = null
            return this
        }

        fun error(error: Throwable): StateData<T> {
            status = DataStatus.ERROR
            data = null
            this.error = error
            return this
        }

        fun complete(): StateData<T> {
            status = DataStatus.COMPLETE
            return this
        }

        enum class DataStatus {
            CREATED, SUCCESS, ERROR, LOADING, COMPLETE
        }

        init {
            status = DataStatus.CREATED
            data = null
            error = null
        }
    }
}