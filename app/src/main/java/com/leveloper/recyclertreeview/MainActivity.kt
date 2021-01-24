package com.leveloper.recyclertreeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.leveloper.library.TreeNode
import com.leveloper.recyclertreeview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.adapter = TreeViewAdapter(getNodeList())

    }

    private fun getNodeList(): List<TreeNode<*>> {
        val nodeList = mutableListOf<TreeNode<*>>()

        nodeList.add(
            TreeNode(Folder(1))
                .addChild(TreeNode(Folder(2)))
                .addChild(TreeNode(File("file")))
        )

        return nodeList
    }
}