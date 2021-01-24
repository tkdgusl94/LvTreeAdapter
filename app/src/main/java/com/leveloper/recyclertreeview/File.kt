package com.leveloper.recyclertreeview

import com.leveloper.library.LayoutItemType

class File(val name: String): LayoutItemType {

    override fun getLayoutId(): Int = R.layout.item_file
}