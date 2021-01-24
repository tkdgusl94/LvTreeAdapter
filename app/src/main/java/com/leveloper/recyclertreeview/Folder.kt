package com.leveloper.recyclertreeview

import com.leveloper.library.LayoutItemType

class Folder(val id: Long): LayoutItemType {
    override fun getLayoutId(): Int = R.layout.item_folder
}