package com.antecer.nekopaw.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.antecer.nekopaw.MainActivity
import com.antecer.nekopaw.R
import com.antecer.nekopaw.api.JsEngine
import kotlinx.coroutines.Dispatchers.IO

class HelloViewModel : ViewModel() {

    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    // onNameChanged is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun onNameChanged(newName: String) {
        _name.value = newName
    }
}

@Composable
fun BookCase(helloViewModel: HelloViewModel = viewModel()) {
    val bookList = arrayListOf<Map<String, String>>()
    for (i in 1..10) {
        bookList.add(
            mapOf(
                "title" to "圣墟 测试测试测试测试测试测试测试测试测试测试测试测试",
                "tips" to "$i",
                "author" to "辰东",
                "node" to "起点中文",
                "mark" to "第一章 沙漠中的彼岸花 测试测试测试测试测试测试测试测试测试测试测试测试",
                "markTime" to "2020-12-12",
                "last" to "第1641章 大世灿烂，上苍寂灭 \uD83D\uDCB0",
                "lastTime" to "2021-02-11"
            )
        )
    }
    Scaffold(
        scaffoldState = rememberScaffoldState(),
        drawerContent = {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val inputValue = remember { mutableStateOf(TextFieldValue()) }
                TextField(
                    inputValue.value,
                    onValueChange = { inputValue.value = it },
                    placeholder = { Text("搜索书名或作者") },
                    keyboardOptions = KeyboardOptions(
                        // 强制使用大写字母
                        capitalization = KeyboardCapitalization.None,
                        // 在我们的键盘中启用自动更正功能
                        autoCorrect = true,
                        // 指定输入类型，例如文本，数字，电话。
                        keyboardType = KeyboardType.Text,
                    ),
                    modifier = Modifier.padding(8.dp).fillMaxWidth().constrainAs(createRefs().component1()) {
                        bottom.linkTo(parent.bottom)
                    },
                    //leadingIcon = { Icon(Icons.Rounded.Search) },
                    trailingIcon = {
                        Icon(Icons.Filled.Search, Modifier.clickable {
                            helloViewModel.onNameChanged(inputValue.value.text)
                        })
                    },
                )

                Text(helloViewModel.name.value?:"",Modifier.fillMaxSize().constrainAs(createRefs().component2()) {
                    top.linkTo(parent.top)
                },)
            }
        },
    ) {
        LazyColumn(contentPadding = PaddingValues(5.dp)) {
            items(bookList) {
                BookCard(it)
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun BookCard(book: Map<String, String>) {
    val cover = imageResource(id = R.raw.default_cover)
    val titleSize = TextUnit.Em(6)
    val infoSize = TextUnit.Em(4)
    val tipSize = TextUnit.Em(3)
    Row(
        Modifier.height(106.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            cover,
            Modifier.fillMaxHeight().clip(RoundedCornerShape(5.dp)).clickable { println("你点击了封面") }
        )
        Spacer(Modifier.width(3.dp))
        ConstraintLayout(
            Modifier.fillMaxWidth().clickable { println("你点击了信息") }
        ) {
            val (title, tips, author, node, mark, markTime, last, lastTime) = createRefs()
            Text(
                book["title"] ?: "",
                Modifier.absolutePadding(2.dp).constrainAs(title) {},
                Color.Black,
                titleSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                book["tips"] ?: "",
                Modifier.constrainAs(tips) { end.linkTo(parent.end); }
                    .padding(vertical = 4.dp)
                    .sizeIn(16.dp, 16.dp)
                    .drawBehind {
                        drawRoundRect(Color(0xB0D00000), cornerRadius = CornerRadius(50F))
                    }
                    .padding(horizontal = 5.dp),
                Color.White,
                tipSize
            )
            Row(
                Modifier.constrainAs(author) { start.linkTo(title.start);top.linkTo(title.bottom) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.AccountBox)
                Text(book["author"] ?: "", fontSize = infoSize, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Text(
                book["node"] ?: "",
                Modifier.constrainAs(node) { end.linkTo(parent.end); baseline.linkTo(author.baseline) }
                    .drawBehind {
                        drawRoundRect(Color(0xB0008888), cornerRadius = CornerRadius(20F))
                    }
                    .padding(horizontal = 4.dp),
                Color.White,
                tipSize
            )
            Row(
                Modifier.constrainAs(mark) { start.linkTo(author.start); top.linkTo(author.bottom) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.MoveToInbox)
                Text(book["mark"] ?: "", fontSize = infoSize, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Text(
                book["markTime"] ?: "",
                Modifier.constrainAs(markTime) { end.linkTo(parent.end); baseline.linkTo(mark.baseline) }
                    .drawBehind {
                        drawRoundRect(Color(0xB0668888), cornerRadius = CornerRadius(20F))
                    }
                    .padding(horizontal = 4.dp),
                Color.White,
                tipSize
            )
            Row(
                Modifier.constrainAs(last) { start.linkTo(mark.start); top.linkTo(mark.bottom) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Satellite)
                Text(book["last"] ?: "", fontSize = infoSize, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Text(
                book["lastTime"] ?: "",
                Modifier
                    .constrainAs(lastTime) { end.linkTo(parent.end); baseline.linkTo(last.baseline) }
                    .drawBehind {
                        drawRoundRect(Color(0xB0662288), cornerRadius = CornerRadius(20F))
                    }
                    .padding(horizontal = 4.dp),
                Color.White,
                tipSize
            )
        }
    }
}