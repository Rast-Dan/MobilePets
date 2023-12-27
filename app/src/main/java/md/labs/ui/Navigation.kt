package md.labs.ui

sealed class Navigation(val route: String) {
    data object Splash : Navigation("splash")
    data object Login : Navigation("login")
    data object List : Navigation("list")
    data object Detail : Navigation("detail")
}