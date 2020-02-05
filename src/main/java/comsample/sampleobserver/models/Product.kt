package comsample.sampleobserver.models


data class Product(
    val id: Int = -1,
    val name: String = "",
    val description: String = "",
    var count: Int = 0,
    var isSelectedItem: Boolean = false
)