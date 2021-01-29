package by.grodno.pvt.site.housingAndCommunalServicesApp.domain;

public enum WorkScale {
    NONE(0), ONE(1), TWO(2),THREE(3), FOUR(4), FLAT(5);

    Integer scale;

    WorkScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }
}
