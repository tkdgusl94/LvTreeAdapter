package com.leveloper.library

class TreeNode<T: LayoutItemType>(
    var content: T
) {
    var parent: TreeNode<*>? = null

    private val _childList = mutableListOf<TreeNode<*>>()
    val childList: List<TreeNode<*>>
        get() = _childList

    var isLocked = false
        private set

    var isExpand = true
        private set

    val isRoot: Boolean
        get() = parent == null

    val isLeaf: Boolean
        get() = _childList.isEmpty()

    fun addChild(node: TreeNode<*>): TreeNode<*> {
        _childList.add(node)
        node.parent = this

        return this
    }

    fun lock() {
        isLocked = true
    }

    fun unlock() {
        isLocked = false
    }

    fun toggle() {
        isExpand = !isExpand
    }

    fun expand() {
        if (!isExpand) isExpand = true
    }

    fun expandAll() {
        expand()

        if (isLeaf) return
        _childList.forEach { it.expandAll() }
    }

    fun collapse() {
        if (isExpand) isExpand = false
    }

    fun collapseAll() {
        collapse()

        if (isLeaf) return
        _childList.forEach { it.collapseAll() }
    }

    override fun toString(): String {
        return "TreeNode(content=$content, parent=$parent, childList=${_childList.size})"
    }
}