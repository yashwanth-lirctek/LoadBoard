package com.lirctek.loadboard.data.identifiers


enum class RoleId(val roleId: Int) {
    DRIVER(4), CARRIER(17), OWNER(14);

    companion object {
        @JvmStatic
        fun fromInt(status: Int): RoleId =
            (values().find { value -> value.roleId == status } ?: RoleId) as RoleId
    }

}
