class LicenseKeyFormatting {
    fun licenseKeyFormatting(licenseKey: String, k: Int): String {
        val lastIndex = licenseKey.lastIndex
        val formatted = StringBuilder()
        var groupCount = 0
        for (i in lastIndex downTo 0) {
            val char = licenseKey[i]
            if (char == '-') continue
            formatted.append(char.toUpperCase())
            groupCount++
            if (groupCount == k) {
                groupCount = 0
                formatted.append('-')
            }
        }
        val result = formatted.toString().reversed()
        return if (result.firstOrNull() == '-') result.drop(1) else result
    }
}