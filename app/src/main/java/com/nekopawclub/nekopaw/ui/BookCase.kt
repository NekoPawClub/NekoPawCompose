package com.nekopawclub.nekopaw.ui

import androidx.annotation.ContentView
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Popup
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.*
import com.nekopawclub.nekopaw.R

class HelloViewModel : ViewModel() {
    companion object {
        val ins: HelloViewModel by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HelloViewModel()
        }
    }

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

@Preview
@Composable
fun BookCase(helloViewModel: HelloViewModel = HelloViewModel.ins) {
    val bookList = arrayListOf<Map<String, String>>()
    for (i in 1..10) {
        bookList.add(
            mapOf(
                "title" to "圣墟$i",
                "tips" to "$i",
                "author" to "辰东",
                "node" to "起点中文",
                "mark" to "第一章 沙漠中的彼岸花",
                "markTime" to "2020-12-12",
                "last" to "第1641章 大世灿烂，上苍寂灭 \uD83D\uDCB0",
                "lastTime" to "2021-02-11"
            )
        )
    }

    var editingText by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            var marginBottom by remember { mutableStateOf(0.dp) }
            Row(
                Modifier.padding(bottom = marginBottom).fillMaxWidth(0.9F).background(Color(0xAF000000)),
                Arrangement.SpaceAround,
                Alignment.CenterVertically
            ) {
                val barHeight = 36.dp
                Icon(Icons.Outlined.AccountCircle, null, Modifier.size(barHeight))
                BasicTextField(
                    editingText,
                    onValueChange = { editingText = it },
                    modifier = Modifier.background(Color(0xDFFFFFFF))
                        .border(1.dp, Color(0xFF000000)).height(barHeight),
                    keyboardOptions = KeyboardOptions(
                        KeyboardCapitalization.None,
                        true,
                        KeyboardType.Text,
                        ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = { println("这里是测试内容: $editingText") }
                    ),
                    onTextLayout = {

                    },
                    onTextInputStarted = {
                        marginBottom = 300.dp
                    },
                    singleLine = true
                )
                Icon(Icons.Filled.Clear, null, Modifier.size(barHeight).clickable {
                    editingText = ""
                })
            }
        },
        bottomBar = {

        },
        scaffoldState = rememberScaffoldState(),
        drawerContent = {
            BookCaseMenu()
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(5.dp)) {
            items(bookList) {
                BookCard(it)
                Spacer(Modifier.height(10.dp))
            }
        }
        it.calculateBottomPadding()
    }
}

@Preview
@Composable
fun BookCaseMenu() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        var editingText by remember { mutableStateOf("") }
        OutlinedTextField(
            editingText,
            onValueChange = { editingText = it },
            modifier = Modifier.padding(8.dp).fillMaxWidth().constrainAs(createRefs().component1()) {
                top.linkTo(parent.top)
            },
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.None,
                true,
                KeyboardType.Text,
                ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { println("这里是测试内容: $editingText") }
            ),
            placeholder = { Text("搜索书名或作者") },
            leadingIcon = {
                Icon(Icons.Outlined.AccountCircle, null)
            },
            trailingIcon = {
                Icon(Icons.Filled.Clear, null, Modifier.clickable {
                    editingText = ""
                })
            },
            singleLine = true
        )
    }
}

@Composable
fun BookCard(book: Map<String, String>) {
    val cover = painterResource(R.raw.default_cover)
    val titleSize = 6.em
    val infoSize = 4.em
    val tipSize = 3.em
    Row(
        Modifier.height(106.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            cover,
            null,
            Modifier.fillMaxHeight().clip(RoundedCornerShape(5.dp)).clickable { println("你点击了封面") }
        )
        Spacer(Modifier.width(3.dp))
        ConstraintLayout(
            Modifier.fillMaxWidth().clickable { println("你点击了信息") }
        ) {
            val (title, tips, author, node, mark, markTime, last, lastTime) = createRefs()
            Text(
                book["title"] ?: "",
                modifier = Modifier.absolutePadding(2.dp).constrainAs(title) {},
                color = Color.Black,
                fontSize = titleSize,
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
                Icon(Icons.Outlined.ContactPage, null)
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
                Icon(Icons.Outlined.Description, null)
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
                Icon(Icons.Outlined.NoteAdd, null)
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