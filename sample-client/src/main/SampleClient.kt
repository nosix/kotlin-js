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
    var computed: Json
}

fun <T : Json> Json(): T = js("({})")
fun <T : Json> Json(init: T.() -> Unit): T = Json<T>().apply(init)

fun Vue(init: VueOption.() -> Unit): Vue = Vue(Json<VueOption>().apply(init))

external interface Model : Json {
    var firstName: String
    var lastName: String
}

external interface Computed : Json {
    var fullName: () -> String
}

inline fun <T> thisAs(): T = js("this")

fun run() {
    println(lodash.capitalize("hello world"))

    val vm: dynamic = Vue {
        el = "#example"
        data = Json<Model> {
            firstName = "Foo"
            lastName = "Bar"
        }
        computed = Json<Computed> {
            // this は Json クラスのインスタンス
            fullName = {
                val self = thisAs<Model>()
                // this は実行時に決定される
                "${self.firstName} ${self.lastName}"
            }
        }
    }
    vm.firstName = "Taro"
    vm.lastName = "Yamada"
}
