package com.example.myapplication

/* Androidで提供されるAPIの機能なのでandroidから始まります */
import android.util.Log

/* 一般的にクラスの名前とファイルの名前は一致させるべきです.またクラス名の頭文字は大文字にします
*  １つのファイルに複数のクラスを書くことも可能です.その場合にはどれかのクラス名をファイル名にしておくべきです */
class Cat (
    /* ここの変数宣言をコンストラクタと呼びます.クラスをインスタンス化するために必要なプロパティを定義しています */
    val name: String,
    val age: Int,
    val gender: String,
    val breed: String
    ){
    fun say(message: String) {
        /* 文字列の中に$変数名を加えるとテンプレートリテラルが使える */
        Log.d("Cat", "$name 「$message」")
    }

    fun sleep() {
        if(age > 2) {
            say("${name}は2歳以上です")
        } else {
            say("${name}は2歳未満です")
        }
    }

    fun greet() {
        Log.d("Cat", "${name}は${gender}で${age}歳で${breed}です。")
    }

    fun talkAbout(cat: Cat) {
        Log.d("Cat", "${cat.name}は${name}と${Math.abs(age - cat.age)}歳差で、${name}が${gender}に対して${cat.name}は${cat.gender}で、${cat.name}は${cat.breed}ですが${name}は${breed}です")
    }

}