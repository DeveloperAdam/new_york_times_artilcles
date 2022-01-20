package com.noor.nytimes.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Adam Noor on 27/12/2021.
 */
@Parcelize
data class NyTimesData(

    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("copyright")
    @Expose
    val copyright: String? = null,
    @SerializedName("num_results")
    @Expose
    val numResults: Int = 0,
    @SerializedName("results")
    @Expose
    val resultData: List<Record> = arrayListOf()
) : Parcelable

@Parcelize
data class Record(
    @SerializedName("uri")
    @Expose
    val uri: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("id")
    @Expose
    val id: Long = 0,
    @SerializedName("asset_id")
    @Expose
    val assetId: Long = 0,
    @SerializedName("source")
    @Expose
    val source: String? = null,
    @SerializedName("published_date")
    @Expose
    val publishedDate: String? = null,
    @SerializedName("updated")
    @Expose
    val updated: String? = null,
    @SerializedName("section")
    @Expose
    val section: String? = null,
    @SerializedName("subsection")
    @Expose
    val subsection: String? = null,
    @SerializedName("nytdsection")
    @Expose
    val nytdsection: String? = null,
    @SerializedName("adx_keywords")
    @Expose
    val adxKeywords: String? = null,
    @SerializedName("column")
    @Expose
    val column : String? = null,
    @SerializedName("byline")
    @Expose
    val byline: String? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("abstract")
    @Expose
    val _abstract: String? = null,
    @SerializedName("des_facet")
    @Expose
    val desFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("org_facet")
    @Expose
    val orgFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("per_facet")
    @Expose
    val perFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("geo_facet")
    @Expose
    val geoFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("media")
    @Expose
    val media: ArrayList<Medium> = arrayListOf(),
    @SerializedName("eta_id")
    @Expose
    var etaId: Int = 0
) : Parcelable

@Parcelize
data class Medium(

    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("subtype")
    @Expose
    val subtype: String? = null,
    @SerializedName("caption")
    @Expose
    val caption: String? = null,
    @SerializedName("copyright")
    @Expose
    val copyright: String? = null,
    @SerializedName("approved_for_syndication")
    @Expose
    var approvedForSyndication: Int = 0,
    @SerializedName("media-metadata")
    @Expose
    val mediaMetadata: ArrayList<MediaMetadatum> = arrayListOf(),
) : Parcelable

@Parcelize
data class MediaMetadatum(
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("format")
    @Expose
    val format: String? = null,
    @SerializedName("height")
    @Expose
    var height: Int = 0,
    @SerializedName("width")
    @Expose
    var width: Int = 0,
) : Parcelable