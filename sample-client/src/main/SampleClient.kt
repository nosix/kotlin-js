import kotlin.js.Json
import kotlin.js.json

@JsNonModule
@JsModule("lodash")
external val lodash: dynamic

@JsNonModule
@JsModule("vue")
external class Vue(option: Json)

fun run() {
    println(lodash.capitalize("hello world"))

    val vm: dynamic = Vue(json(
            "el" to "#example",
            "data" to json(
                    "message" to "Hello World"
            )
    ))
    vm.message = "Hello Kotlin World"
}
