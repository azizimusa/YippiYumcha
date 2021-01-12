package yumcha.toga.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListingPojo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("isClose")
    @Expose
    private Boolean isClose;
    @SerializedName("closeLabel")
    @Expose
    private String closeLabel;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productDesc")
    @Expose
    private String productDesc;
    @SerializedName("star")
    @Expose
    private Double star;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("promoDesc")
    @Expose
    private String promoDesc;
    @SerializedName("outletAround")
    @Expose
    private Integer outletAround;
    @SerializedName("outletDesc")
    @Expose
    private Integer outletDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getClose() {
        return isClose;
    }

    public void setClose(Boolean close) {
        isClose = close;
    }

    public String getCloseLabel() {
        return closeLabel;
    }

    public void setCloseLabel(String closeLabel) {
        this.closeLabel = closeLabel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPromoDesc() {
        return promoDesc;
    }

    public void setPromoDesc(String promoDesc) {
        this.promoDesc = promoDesc;
    }

    public Integer getOutletAround() {
        return outletAround;
    }

    public void setOutletAround(Integer outletAround) {
        this.outletAround = outletAround;
    }

    public Integer getOutletDesc() {
        return outletDesc;
    }

    public void setOutletDesc(Integer outletDesc) {
        this.outletDesc = outletDesc;
    }
}
