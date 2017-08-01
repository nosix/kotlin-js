import kotlin.js.Json

@JsNonModule
@JsModule("lodash")
external val lodash: dynamic

@JsNonModule
@JsModule("vue")
external open class Vue(option: VueOption)

external interface VueOption : Json {
    var el: Any
    var data: Json
}

fun <T : Json> Json(): T = js("({})")
fun <T : Json> Json(init: T.() -> Unit): T = Json<T>().apply(init)

fun Vue(init: VueOption.() -> Unit): Vue = Vue(Json<VueOption>().apply(init))

external interface Model : Json {
    var message: String
}

fun run() {
    println(lodash.capitalize("hello world"))

    val vm: dynamic = Vue {
        el = "#example"
        data = Json<Model> {
            message = "Hello World"
        }
    }
    vm.message = "Hello Kotlin World"
}
