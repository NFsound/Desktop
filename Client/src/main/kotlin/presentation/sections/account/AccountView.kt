package presentation.sections.account

import models.core.account.Account
import presentation.sections.common.SectionView

interface AccountView: SectionView {

    fun printAllUsers(list: List<Account>)

    fun initializeUserInfo(account: Account)

    fun hideAccountUI()

    fun showAccountUI()
}