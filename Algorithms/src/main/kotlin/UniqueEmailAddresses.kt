class UniqueEmailAddresses {
    fun numUniqueEmails(emails: Array<String>): Int {
        val validUnique = mutableSetOf<String>()
        for (email in emails) {
            validUnique.add(fix(email))
        }
        return validUnique.size
    }

    /**
     * 1 <= emails.length <= 100
     * 1 <= emails[i].length <= 100
     * emails[i] consist of lowercase English letters, '+', '.' and '@'.
     * Each emails[i] contains exactly one '@' character.
     * All local and domain names are non-empty.
     * Local names do not start with a '+' character.
     * Domain names end with the ".com" suffix.
     */
    private fun fix(
        email: String
    ): String {
        val (local, domain) = email.split("@")
        var localFixed = ""
        for (c in local) {
            if (c == '+') break
            if (c == '.') continue
            localFixed += c
        }
        return "$localFixed@$domain"
    }
}