package logic.utils


enum class TransitionType(val selector: String){
    EVERY_DAY("Every Day"),
    SPECIFIC_DAYS("Specific Days"),
    INTERVAL("Interval"),
    AS_NEEDED("As Needed")
}