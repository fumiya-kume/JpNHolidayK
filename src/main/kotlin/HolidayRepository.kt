interface HolidayRepository {
    fun getHolidayList(): List<Holiday>

    companion object {
        @JvmStatic
        fun getInstance(): HolidayRepository = HolidayRepositoryImpl()
    }
}
