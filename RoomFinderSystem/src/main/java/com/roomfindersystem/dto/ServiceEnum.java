package com.roomfindingsystem.dto;

public enum ServiceEnum {
    TUDO("Tự do", 1, "mdi:clock"),
    KHONGCHUNGCHU("Không chung chủ", 2, "fa6-solid:house-circle-xmark"),
    BAOVE("Bảo vệ", 3, "mdi:security-account"),
    KHOAVANTAY("Khóa vân tay", 4, "material-symbols:fingerprint"),
    CAMERA("Camera an ninh", 5, "bxs:cctv"),
    TULANH("Tủ lạnh", 6, "mdi:fridge-variant"),
    GIUONGNGU("Giường ngủ", 7, "material-symbols:bed"),
    MAYGIAT("May giặt", 8, "icon-park-outline:washing-machine-one"),
    BINHNONGLANH("Bình nóng lạnh", 9, "streamline:hotel-bed-2"),
    DIEUHOA("Điều hòa", 10, "streamline:hotel-air-conditioner"),
    GACXEP("Gác xếp", 11, "game-icons:stairs"),
    BANHOC("Bàn học", 12, "game-icons:table"),
    TUQUANAO("Tủ quần áo", 13, "streamline:shelf"),

    WIFI("Wifi", 14, "material-symbols:wifi"),
    BEPGAS("Bếp gas", 18, "mdi:cooker");


    private final String displayName;
    private final int serviceId;
    private final String iconClass;

    ServiceEnum(String displayName, int serviceId, String iconClass) {
        this.displayName = displayName;
        this.serviceId = serviceId;
        this.iconClass = iconClass;
    }


    public String getDisplayName() {
        return displayName;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getIconClass() {
        return iconClass;
    }
}
