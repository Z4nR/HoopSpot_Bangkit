package com.zulham.hoopspot.vo

data class Resource<T>(val status: StatusVo, val data :T?, val message: String?){
    companion object{
        fun <T> success(data: T?): Resource<T> = Resource(StatusVo.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(StatusVo.ERROR, data, msg)

        fun<T> loading(data: T?): Resource<T> = Resource(StatusVo.LOADING, data, null)
    }
}