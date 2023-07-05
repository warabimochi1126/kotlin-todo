package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
/* kotlin標準のパッケージはkotlinから始まります */
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /* ②作成したUI用の関数をこの場所で呼び出す(setContentの中かつSurface()の内側でない場所ならどこでも良いです) */
                    MyTodoApp()
                }
            }
        }
    }
}

/* JetpackComposeを使ってUIを作成する場合はComposableという単位でUIを作成します(Composable=UIという意味でOK)
*  Composableを作成する時は@Composableというアノテーションを付けて関数名の頭文字を大文字にします */

// 追加したTextFieldという部品が試験的な物で今後変更される可能性があるという事を示すアノテーションです(付けてないとビルド出来ないです)
@OptIn(ExperimentalMaterial3Api::class)
// @Composableというアノテーションを追加
// 関数を宣言して大文字で始める
@Composable
/* ①UI用の関数を作成する */
fun MyTodoApp() {
    //remeber{}はComposableが更新されても{}内のデータを保持するという意味.mutableStateOf("")は状態である事を宣言している
    val todo = remember{ mutableStateOf("")}

    //todoの一覧は複数のテキストを扱うので状態を配列で持ちます.mutableStateListOf()は配列で状態を作成します.<String>で配列の型は文字列を指定しています
    val todoList = remember { mutableStateListOf<String>() }

    // タイトルをトップバーの中にいれて表示します.Scaffoldは画面全体のUIを組むために使います
    Scaffold(
        //トップバーの引数
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "My TODO",
                    color = Color.White,
                )},
                // TopAppBarの引数にcolorsがあるので色を引き渡すと背景色を変更出来ます
                colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xFF000800))
            )
        }
    ) {paddingValues ->
        //Columnは中身のComposableを上から順番に並べてくれるComposableです
        Column (modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)         //上と横に16dpの幅を設定する
        ){
            // 文字サイズはModifierを使って設置出来ません.どんな部品でも共通の設定ならModifier,部品特有の設定なら引数で設定すると考えておくと良いです
            // 追加ボタンをフォームの横に配置します.Rowの中に置かれたComposableは左から順に配置されます.verticalAlignmentに渡した値によって各要素の揃え方が変わります
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    // 保存された値をtodo.valueの値をフォームに代入している(見えるようにしている)
                    value = todo.value,

                    // フォームに入力された内容をtodo.valueに保存しています.入力内容がtextに入ってtodo.value=textで入力内容がtodo.valueに代入されます
                    // onValueChangeに渡した処理が1文字ごとに実行されています
                    onValueChange = {text -> todo.value = text},
                    // JetpackComposeで見た目を整えるにはModifierを使います.作成したComposableをModify(修正)するのがModifierという意味です
                    modifier = Modifier.padding(end = 16.dp)           //上下に余白を付ける
                )

                //todoList.add()で追加ボタンを押した時にフォームに入力されていたデータがtodoListに保存されます
                Button(onClick = {
                    todoList.add(todo.value)
                    todo.value = ""
                }) {
                    Text(text = "追加")
                }
            }

            //追加したtodoの内容を保存しています
            todoList.forEach{ item ->
                Text(text = item)
            }
        }
    }
}

/* プレビュー用のComposableには@Previewというアノテーションをつける(アノテーションには意味があります) */
// @Previewというアノテーションを追加.(showBackground = true)は"背景付きのプレビューを表示する"という設定です
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        /* ③AndoridStudio側からプレビュー出来るようにUI用の関数を渡す */
        MyTodoApp()
    }
}