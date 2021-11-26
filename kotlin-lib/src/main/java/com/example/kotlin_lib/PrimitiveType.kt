package com.example.kotlin_lib

/**
 * 功能描述
 *
 * @author Administrator
 * @since 2022-06-21
 */
enum class PrimitiveType(
        val hprofType: Int,
        val byteSize: Int
) {
    BOOLEAN(4, 1),
    CHAR(5, 2),
    FLOAT(6, 4),
    DOUBLE(7, 8),
    BYTE(8, 1),
    INT(10, 4),
    LONG(11, 8);

    companion object {
        const val REFERENCE_HPROF_TYPE = 2
        val byteSizeByHprofType = values().map { it.hprofType to it.byteSize }.toMap()
        val primitiveTypeByHprofType= values().map { it.hprofType to it }.toMap()
    }
}

/*
enum class PrimitiveType(
    val hprofType: Int,
    val byteSize: Int
) {
    BOOLEAN(4, 1),
    CHAR(5, 2),
    FLOAT(6, 4),
    DOUBLE(7, 8),
    BYTE(8, 1),
    SHORT(9, 2),
    INT(10, 4),
    LONG(11, 8);

    companion object {
        const val REFERENCE_HPROF_TYPE = 2
        val byteSizeByHprofType = values().map { it.hprofType to it.byteSize }.toMap()
        val primitiveTypeByHprofType = values().map { it.hprofType to it }.toMap()
    }
}
 */